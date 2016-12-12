package com.pa.jk.pay.storage;

import java.io.IOException;

import com.pa.jk.pay.bean.OrderInfo;

/**
 * 缓存
 * 
 * @author zhangjun19
 *
 */
public interface OrderCacheStorage {

	boolean cache(OrderInfo order) throws IOException;

	OrderInfo getByOrderNo(String orderNo) throws InstantiationException, IllegalAccessException, IOException;

	public boolean remove(String orderNo);
}
