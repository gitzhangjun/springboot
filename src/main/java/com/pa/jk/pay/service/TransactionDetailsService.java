package com.pa.jk.pay.service;

import java.util.List;

import com.pa.jk.pay.bean.TransactionDetails;

/**
 * 支付宝-交易详情
 * 
 * @author zhangjun19
 *
 */
public interface TransactionDetailsService {
	/**
	 * 保存通知
	 * 
	 * @param transactionDetails
	 * @return
	 */
	boolean save(TransactionDetails transactionDetails);

	/**
	 * 根据通知ID获取通知详情
	 * 
	 * @param notifyId
	 * @return
	 */
	TransactionDetails getByNotifyId(String notifyId);

	/**
	 * 根据订单获取通知详情
	 * 
	 * @param orderId
	 * @return
	 */
	List<TransactionDetails> getByOrderNo(String orderId);

	
}
