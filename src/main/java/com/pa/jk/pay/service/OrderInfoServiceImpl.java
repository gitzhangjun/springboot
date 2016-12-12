package com.pa.jk.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa.jk.pay.bean.OrderInfo;
import com.pa.jk.pay.storage.OrderDbStorage;

@Service("orderInfo")
public class OrderInfoServiceImpl implements OrderInfoService {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderInfoServiceImpl.class);

	@Autowired
	private OrderDbStorage orderDbStorage;

	@Override
	public OrderInfo getByOrderNo(String orderNo) {
		return orderDbStorage.getByOrderNo(orderNo);
	}

	/**
	 * 保存订单
	 * 
	 * @param orderNo
	 *            订单编号
	 * @param subject
	 *            商品名描述
	 * @param body
	 *            商品描述
	 * @param totalFee
	 *            价格
	 * @return
	 */
	public boolean save(long uid, String orderNo, String subject, String body, double totalFee) {
		try {
			OrderInfo order = new OrderInfo(uid, orderNo, subject, body, totalFee);
			return orderDbStorage.save(order);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("OrderInfoServiceImpl save err ", e);
			return false;
		}
	}

	@Override
	public boolean save(OrderInfo orderInfo) {
		try {
			return	orderDbStorage.save(orderInfo);
		} catch (Exception e) {
			logger.error("OrderInfoServiceImpl save err ", e);
			return false;
		}
	}

	@Override
	public List<OrderInfo> getByUid(long uid) {
		// TODO Auto-generated method stub
		return orderDbStorage.getByUid(uid);
	}

	@Override
	public OrderInfo updateStatusByOrderNo(String orderNo, byte status) {
		return orderDbStorage.updateStatusByOrderNo(orderNo, status);
	}
}
