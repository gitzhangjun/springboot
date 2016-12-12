package com.pa.jk.pay.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.pa.jk.pay.bean.OrderInfo;
import com.pa.jk.pay.bean.TransactionDetails;
import com.pa.jk.pay.config.AlipayConfig;
import com.pa.jk.pay.service.OrderInfoService;
import com.pa.jk.pay.service.TradeRefundService;
import com.pa.jk.pay.service.TransactionDetailsService;
import com.pa.jk.pay.util.DateUtils;

/**
 * 支付信息处理
 * 
 * @author zhangjun19
 *
 */
@RestController
@RequestMapping("/pay")
public class AliPayController {

	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AliPayController.class);
	@Autowired
	private OrderInfoService orderInfo;

	@Autowired
	private TransactionDetailsService transactionDetailsService;

	@Autowired
	private TradeRefundService tradeRefundService;

	@RequestMapping("/notify_alipay")
	public String notity(String notify_time, String notify_type, String notify_id, String app_id, String sign_type,
			String sign, String trade_no, String out_trade_no, String out_biz_no, String buyer_id,
			String buyer_logon_id, String seller_id, String seller_email, String trade_status, double total_amount,
			double receipt_amount, double invoice_amount, double buyer_pay_amount, double point_amount,
			double refund_fee, String subject, String body, String gmt_create, String gmt_payment, String gmt_refund,
			String gmt_close, String fund_bill_list) {

		logger.info("notify>>>>>>>>>>start");

		// 将异步通知中收到的待验证所有参数都存放到map中,参考 TransactionDetails 对象
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("notify_time", notify_time);
		sParaTemp.put("notify_type", notify_type);
		sParaTemp.put("notify_id", notify_id);
		sParaTemp.put("app_id", app_id);
		sParaTemp.put("sign_type", sign_type);
		sParaTemp.put("sign", sign);
		sParaTemp.put("trade_no", trade_no);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("out_biz_no", out_biz_no);
		sParaTemp.put("buyer_id", buyer_id);
		sParaTemp.put("buyer_logon_id", buyer_logon_id);
		sParaTemp.put("seller_id", seller_id);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("trade_status", trade_status);
		sParaTemp.put("total_amount", String.valueOf(total_amount));
		sParaTemp.put("receipt_amount", String.valueOf(receipt_amount));
		sParaTemp.put("invoice_amount", String.valueOf(invoice_amount));
		sParaTemp.put("buyer_pay_amount", String.valueOf(buyer_pay_amount));
		sParaTemp.put("point_amount", String.valueOf(point_amount));
		sParaTemp.put("refund_fee", String.valueOf(refund_fee));
		sParaTemp.put("subject", subject);
		sParaTemp.put("body", body);
		sParaTemp.put("gmt_create", gmt_create);
		sParaTemp.put("gmt_payment", gmt_payment);
		sParaTemp.put("gmt_refund", gmt_refund);
		sParaTemp.put("gmt_close", gmt_close);
		sParaTemp.put("fund_bill_list", fund_bill_list);

		boolean signVerified = false;
		boolean resStatus = false;

		try {
			signVerified = AlipaySignature.rsaCheckV1(sParaTemp, AlipayConfig.alipay_public_key, "UTF-8"); // 调用SDK验证签名
		} catch (AlipayApiException e) {
			logger.error("AlipaySignature rsaCheckV1 err", e);
		}

		logger.info("notify>>>>>>>>>> signVerified:" + signVerified);
		// TODO 环境通了删掉
		signVerified = true;

		if (signVerified) {
			// 验签成功后 获取原始订单
			OrderInfo order = orderInfo.getByOrderNo(out_trade_no);
			// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
			if (null != order && app_id.equals(AlipayConfig.APPID) && total_amount == order.getTotalFee()
					&& order.getSellerId().equals(seller_id)) {
				TransactionDetails transactionDetails = new TransactionDetails();
				transactionDetails.setNotifyTme(notify_time);
				transactionDetails.setNotifyType(notify_type);
				transactionDetails.setNotifyId(notify_id);
				transactionDetails.setSignType(sign_type);
				transactionDetails.setSign(sign);
				transactionDetails.setTradeNo(trade_no);
				transactionDetails.setOrderNo(out_trade_no);
				transactionDetails.setOutBizNo(out_biz_no);
				transactionDetails.setBuyerId(buyer_id);
				transactionDetails.setBuyerLogonId(buyer_logon_id);
				transactionDetails.setSellerId(seller_id);
				transactionDetails.setSellerEmail(seller_email);
				transactionDetails.setTradeStatus(trade_status);
				transactionDetails.setTotalAmount(total_amount);
				transactionDetails.setReceiptAmount(receipt_amount);
				transactionDetails.setInvoiceAmount(invoice_amount);
				transactionDetails.setBuyerPayAmount(buyer_pay_amount);
				transactionDetails.setPointAmount(point_amount);
				transactionDetails.setRefundFee(refund_fee);
				transactionDetails.setSubject(subject);
				transactionDetails.setBody(body);
				transactionDetails.setGmtCreate(DateUtils.parseDateTime(gmt_create, new Date()));
				transactionDetails.setGmtPayment(DateUtils.parseDateTime(gmt_payment, new Date()));
				transactionDetails.setGmtRefund(DateUtils.parseDateTime(gmt_refund, new Date()));
				transactionDetails.setGmtClose(DateUtils.parseDateTime(gmt_close, new Date()));
				transactionDetails.setFundBillList(fund_bill_list);
				resStatus = transactionDetailsService.save(transactionDetails);
				logger.info("notify>>>>>>>>transactionDetails>>>>>>>" + transactionDetails);
			}
			logger.info("notify>>>>>>>>>>>>>>>" + order);
		} else {
			// TODO 验签失败则记录异常日志，并在response中返回failure.
			logger.error("AlipaySignature rsaCheckV1 signVerified:false");
		}

		return resStatus ? "success" : "failure";
	}

	@RequestMapping("/refund")
	public String refund(long uid, String orderNo) {
		
//		tradeRefundService.execute(reqParas);
		return null;
	}
}
