package com.pa.jk.pay.storage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa.jk.pay.bean.OrderInfo;
import com.pa.jk.pay.config.OrderInfoConstants;
import com.pa.jk.pay.util.RedisUtils;
import com.pa.jk.pay.util.protostuff.ProtostuffClient;

/**
 * 缓存
 * 
 * @author zhangjun19
 *
 */

@Service("orderCacheStorage")
public class OrderCacheStorageImpl implements OrderCacheStorage {
	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private ProtostuffClient protostuffClient;

	@Override
	public boolean cache(OrderInfo order) throws IOException {
		byte[] ftBytes = protostuffClient.serialize(order);
		if (null == ftBytes) {
			return false;
		}
		return redisUtils.set(getKey(order.getOrderNo()), ftBytes);
	}

	@Override
	public OrderInfo getByOrderNo(String orderNo) throws InstantiationException, IllegalAccessException, IOException {
		Object obj = redisUtils.get(getKey(orderNo));
		if (null != obj) {
			OrderInfo order = protostuffClient.deSerialize((byte[]) obj, OrderInfo.class);
			return order;
		}
		return null;
	}

	public boolean remove(String orderNo) {
		try {
			redisUtils.remove(getKey(orderNo));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private String getKey(String orderNo) {
		return OrderInfoConstants.ORDER_INFO_REDIS_KEY_PIX + orderNo;
	}
}
