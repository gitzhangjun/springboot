package com.pa.jk.pay.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pa.jk.pay.bean.OrderInfo;

/**
 * XML 配置方式
 * 
 * @author zhangjun19
 *
 */
@Component
public class OrderInfoXmlMapper {
	@Autowired
	private SqlSessionTemplate paySqlSessionTemplate;

	public OrderInfo getOrderInfoBySellerId(long id) {
		return paySqlSessionTemplate.selectOne("getOrderInfoBySellerId", id);
	}
}
