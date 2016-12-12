package com.pa.jk.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa.jk.pay.bean.TransactionDetails;
import com.pa.jk.pay.bean.TransactionDetails.TradeStatus;
import com.pa.jk.pay.dao.TransactionDetailsMapper;

/**
 * 支付宝交易详情通知业务
 * 
 * @author zhangjun19
 *
 */
@Service("transactionDetailsService")
public class TransactionDetailsServiceImpl implements TransactionDetailsService {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TransactionDetailsServiceImpl.class);

	@Autowired
	private TransactionDetailsMapper transactionDetailsMapper;
	@Autowired
	private OrderInfoService orderInfo;

	/**
	 * @param transactionDetails
	 *            保存支付宝通知信息详情</br>
	 *            根据通知更新订单状态
	 * @return
	 */
	@Override
	public boolean save(TransactionDetails transactionDetails) {
		try {
			// 剔除重复通知，并记录警告日志
			if (null != transactionDetailsMapper.getByNotifyId(transactionDetails.getNotifyId())) {
				logger.warn("repeat notify !!! TransactionDetails:" + transactionDetails.toString());
				return true;
			}
			// save
			transactionDetailsMapper.save(transactionDetails);
			// 订单状态更新
			orderInfo.updateStatusByOrderNo(transactionDetails.getOrderNo(),
					TradeStatus.parseDescValue(transactionDetails.getTradeStatus()).value());
			
		} catch (Exception e) {
			logger.error("TransactionDetailsServiceImpl.save transactionDetails err ", e);
			return false;
		}
		return true;
	}

	/**
	 * 根据通知ID获取通知详情,</br>
	 * 每个通知ID只要返回已接受，支付宝不会重复发出，
	 * 
	 * @param notifyId
	 * @return
	 */
	@Override
	public TransactionDetails getByNotifyId(String notifyId) {
		try {
			return transactionDetailsMapper.getByNotifyId(notifyId);
		} catch (Exception e) {
			logger.error("TransactionDetailsServiceImpl.getByNotifyId transactionDetails err ", e);
			return null;
		}
	}

	/**
	 * 根据订单号获取相应的通知列表，</br>
	 * 一个成功的订单至少有一条或以上记录
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public List<TransactionDetails> getByOrderNo(String orderId) {
		try {
			return transactionDetailsMapper.getByOrderNo(orderId);
		} catch (Exception e) {
			logger.error("TransactionDetailsServiceImpl.getByOrderNo transactionDetails err ", e);
			return null;
		}
	}

}
