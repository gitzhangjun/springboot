package com.pa.jk.pay.service;

import java.util.List;

import com.pa.jk.pay.bean.TradeRefund;

/**
 * 退款记录
 * 
 * @author zhangjun19
 *
 */
public interface TradeRefundService {

	boolean save(TradeRefund tradeRefund);

	TradeRefund getByOrderNo(String orderNo);

	List<TradeRefund> getByTradeNo(String TradeNo);

}
