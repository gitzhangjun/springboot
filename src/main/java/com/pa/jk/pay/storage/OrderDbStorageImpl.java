package com.pa.jk.pay.storage;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa.jk.pay.bean.OrderInfo;
import com.pa.jk.pay.bean.TransactionDetails.TradeStatus;
import com.pa.jk.pay.dao.OrderInfoMapper;

/**
 * 存储
 * 
 * @author zhangjun19
 *
 */
@Service("orderDbStorage")
public class OrderDbStorageImpl implements OrderDbStorage {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderDbStorageImpl.class);

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private OrderCacheStorage orderCacheStorage;

	@Override
	public List<OrderInfo> getByUid(long uid) {
		// TODO Auto-generated method stub
		return orderInfoMapper.getByUid(uid);
	}

	@Override
	public boolean save(OrderInfo orderInfo) {
		try {
			orderInfoMapper.save(orderInfo);
		} catch (Exception e) {
			logger.error("OrderInfoServiceImpl save err ", e);
			return false;
		}
		return false;
	}

	@Override
	public OrderInfo getByOrderNo(String orderNo) {
		OrderInfo order = null;
		try {

			order = orderCacheStorage.getByOrderNo(orderNo);
			if (null == order) {
				order = orderInfoMapper.getByOrderNo(orderNo);
				orderCacheStorage.cache(order);
			}
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			logger.error("OrderDbStorageImpl.getByOrderNo err", e);
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public OrderInfo updateStatusByOrderNo(String orderNo, byte status) {
		OrderInfo order = orderInfoMapper.getByOrderNo(orderNo);
		if (null == order || TradeStatus.TRADE_CLOSED.equals(order.getStatus())) {// 已经关闭的订单不做update操作
			logger.warn("OrderDbStorageImpl.updateStatusByOrderNo Has been closed update failure !!!");
			return order;
		}
		//
		int value = orderInfoMapper.updateStatusByOrderNo(orderNo, status);
		if (value > 0) {
			order.setStatus(status);
			orderCacheStorage.remove(orderNo);// 删除缓存
		}
		return order;
	}

}
