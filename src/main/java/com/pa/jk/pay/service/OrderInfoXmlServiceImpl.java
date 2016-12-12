package com.pa.jk.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa.jk.pay.bean.OrderInfo;
import com.pa.jk.pay.dao.OrderInfoXmlMapper;

@Service("orderInfoXmlService")
public class OrderInfoXmlServiceImpl implements OrderInfoXmlService {
	@Autowired
	private OrderInfoXmlMapper orderInfoXmlMapper;

	@Override
	public OrderInfo getOrderInfoBySellerId(long id) {
		// TODO Auto-generated method stub
		return orderInfoXmlMapper.getOrderInfoBySellerId(id);
	}

}
