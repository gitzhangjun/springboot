package com.pa.jk.pay.storage;

import java.util.List;

import com.pa.jk.pay.bean.OrderInfo;

/**
 * 订单数据库逻辑层
 * 
 * @author zhangjun19
 *
 */
public interface OrderDbStorage {

	List<OrderInfo> getByUid(long uid);

	boolean save(OrderInfo orderInfo);

	OrderInfo getByOrderNo(String orderNo);

	OrderInfo updateStatusByOrderNo(String orderNo, byte status);
}
