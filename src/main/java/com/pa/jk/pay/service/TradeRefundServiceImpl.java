package com.pa.jk.pay.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.pa.jk.pay.bean.OrderInfo;
import com.pa.jk.pay.bean.TradeRefund;
import com.pa.jk.pay.bean.TransactionDetails.TradeStatus;
import com.pa.jk.pay.dao.OrderInfoMapper;
import com.pa.jk.pay.dao.TradeRefundMapper;
import com.pa.jk.pay.remote.RemoteTradeRefundQueryService;
import com.pa.jk.pay.remote.RemoteTradeRefundService;
import com.pa.jk.pay.remote.parameter.TradeRefundParas;
import com.pa.jk.pay.remote.parameter.TradeRefundQueryParas;
import com.pa.jk.pay.util.StringUtils;

/**
 * 退款业务
 * 
 * @author zhangjun19
 *
 */
@Service("tradeRefundService")
public class TradeRefundServiceImpl implements TradeRefundService {

	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TradeRefundServiceImpl.class);

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private TradeRefundMapper tradeRefundMapper;

	@Autowired
	private RemoteTradeRefundService remoteTradeRefundService;

	@Autowired
	private RemoteTradeRefundQueryService remoteTradeRefundQueryService;

	@Override
	public boolean save(TradeRefund tradeRefund) {
		try {
			if (null != tradeRefundMapper.getOrderInfoByOrderNo(tradeRefund.getOrderNo())) {
				return true;
			}
			tradeRefundMapper.save(tradeRefund);
			return true;
		} catch (Exception e) {
			logger.error("TradeRefundServiceImpl save err ", e);
		}
		return false;
	}

	/**
	 * 退款接口 // 1 . 确定订单是否存在，权限是否够 // 2 . 确定支付状态 // 3 . 调用远程退款接口 // 4 . 保存退款信息
	 * 
	 * @return
	 */
	public boolean execute(long uid, String orderNo) {
		try {
			// 1. 确定订单是否存在，权限是否够
			OrderInfo order = orderInfoMapper.getByOrderNo(orderNo);//
			if (null != order) {
				logger.debug("TradeRefundServiceImpl.execute >>>> order info:" + order.toString());
				if (order.getUid() != uid) {// 发出警告信息
					logger.warn(
							String.format("TradeRefundServiceImpl.execute >>>> UID:%s, ORDER UID:%s   >> order info:%s",
									uid, order.getUid(), order.toString()));
				}
				// 2 . 确定支付状态
				if (TradeStatus.TRADE_SUCCESS.equals(order.getStatus())) {
					TradeRefundParas reqParas = new TradeRefundParas();
					reqParas.setRefundAmount(String.valueOf(order.getTotalFee()));
					reqParas.setOrderNo(orderNo);
					// 3 . 调用远程退款接口
					AlipayTradeRefundResponse resp = (AlipayTradeRefundResponse) remoteTradeRefundService
							.execute(reqParas);
					// 4. 保存退款信息
					TradeRefund tradeRefund = parseResponse(resp);
					tradeRefundMapper.save(tradeRefund);
					logger.debug("TradeRefundServiceImpl.execute >>>> success!!");
					return true;
				}
				logger.warn("TradeRefundServiceImpl.execute >>>> failure!! msg : Trade Status not TRADE_SUCCESS ");
			}
			logger.warn("TradeRefundServiceImpl.execute >>>> failure!!  msg : There is no Order !!");
			return false;
		} catch (Exception e) {
			logger.error("TradeRefundServiceImpl save err ", e);
		}
		return false;
	}

	/**
	 * TODO 退款信息查询 1. 如果本地数据库中没有，1.1 去远程调接口查询一次, 1.2 保存至DB
	 */
	@Override
	public TradeRefund getByOrderNo(String orderNo) {
		//
		TradeRefund tradeRefund = tradeRefundMapper.getOrderInfoByOrderNo(orderNo);
		if (null == tradeRefund) {// 1. 如果本地数据库中没有
			logger.warn("TradeRefundServiceImpl.getByOrderNo There is no local ");
			TradeRefundQueryParas tradeRefundQueryParas = new TradeRefundQueryParas();
			tradeRefundQueryParas.setOrderNo(orderNo);
			// 1.1 去远程调接口查询一次
			AlipayTradeFastpayRefundQueryResponse resp = (AlipayTradeFastpayRefundQueryResponse) remoteTradeRefundQueryService
					.execute(tradeRefundQueryParas);
			tradeRefund = new TradeRefund();
			tradeRefund.setRefundFee(StringUtils.parseToDouble(resp.getTotalAmount(), 0));
			tradeRefund.setSendBackFee(resp.getRefundAmount());
			tradeRefund.setOrderNo(resp.getOutTradeNo());
			tradeRefund.setRefundReason(resp.getRefundReason());
			tradeRefundMapper.save(tradeRefund);// 1.2 保存至DB
			logger.warn("TradeRefundServiceImpl.getByOrderNo  remoteTradeRefundQueryService resp "
					+ tradeRefund.toString());
		}
		return tradeRefund;
	}

	@Override
	public List<TradeRefund> getByTradeNo(String TradeNo) {
		return tradeRefundMapper.getOrderInfoByTradeNo(TradeNo);
	}

	/**
	 * 本地格式化
	 * 
	 * @param list
	 * @return
	 */
	private String getRefundDetailItemList(List<TradeFundBill> list) {
		StringBuffer str = new StringBuffer("{[");
		for (TradeFundBill tradeFundBill : list) {
			str.append("{\"fund_channel\":\"");
			str.append(tradeFundBill.getFundChannel()).append("\"");
			str.append("\"amount\":\"");
			str.append(tradeFundBill.getAmount()).append("\"");
			str.append("\"real_amount\":\"");
			str.append(tradeFundBill.getRealAmount()).append("\"},");
		}
		str.deleteCharAt(str.length() - 1);
		str.append("]}");
		return str.toString();
	}

	/**
	 * 转换成本地业务对象
	 * 
	 * @param resp
	 * @return
	 */
	private TradeRefund parseResponse(AlipayTradeRefundResponse resp) {
		TradeRefund tradeRefund = new TradeRefund();
		tradeRefund.setOrderNo(resp.getOutTradeNo());
		tradeRefund.setTradeNo(resp.getTradeNo());
		tradeRefund.setBuyerLogonId(resp.getBuyerLogonId());
		tradeRefund.setFundChange(resp.getFundChange());
		tradeRefund.setRefundFee(Double.parseDouble(resp.getRefundFee()));
		tradeRefund.setCtime(new Date());
		tradeRefund.setGmtRefundPay(resp.getGmtRefundPay());
		tradeRefund.setRefundDetailItemList(getRefundDetailItemList(resp.getRefundDetailItemList()));
		tradeRefund.setStoreName(resp.getStoreName());
		tradeRefund.setBuyerUserId(resp.getBuyerUserId());
		tradeRefund.setSendBackFee(resp.getSendBackFee());
		tradeRefund.setOperatorId(resp.getOpenId());
		return tradeRefund;
	}
}
