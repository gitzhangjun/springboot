package com.pa.jk.pay.service;

import java.util.List;

import com.pa.jk.pay.bean.OrderInfo;

/**
 * 订单信息
 * 
 * @author zhangjun19
 *
 */
public interface OrderInfoService {
	/**
	 * 
	 * @param uid
	 * @param orderNo
	 * @param subject
	 * @param body
	 * @param totalFee
	 * @return
	 */
	public boolean save(long uid, String orderNo, String subject, String body, double totalFee);

	boolean save(OrderInfo orderInfo);

	/**
	 * 根据订单号获取订单详情
	 * 
	 * @param orderNo
	 * @return
	 */
	OrderInfo getByOrderNo(String orderNo);

	/**
	 * 个人订单查询
	 * 
	 * @param uid
	 * @return
	 */
	List<OrderInfo> getByUid(long uid);

	/**
	 * 修改订单状态
	 * 
	 * @param ORDER_NO
	 * @param STATUS
	 * @return
	 */
	OrderInfo updateStatusByOrderNo(String orderNo, byte status);
}
