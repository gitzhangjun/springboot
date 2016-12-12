package com.pa.jk.pay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.pa.jk.pay.bean.OrderInfo;
import com.pa.jk.pay.service.OrderInfoService;
import com.pa.jk.pay.service.OrderInfoXmlService;
import com.pa.jk.pay.util.StringUtils;

/**
 * 订单
 * 
 * @author zhangjun19
 *
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {

	@Autowired
	private OrderInfoService orderInfo;

	@Autowired
	private OrderInfoXmlService orderInfoXmlService;

	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderInfoController.class);

	@RequestMapping("/save")
	public String save(long uid, String orderNo, String subject, String body, double totalFee) {
		return orderInfo.save(uid, orderNo, subject, body, totalFee) ? "success" : "failure";
	}

	@RequestMapping("/list")
	public String list(long uid) {

		List<OrderInfo> list = orderInfo.getByUid(uid);
		if (null != list) {
			return JSON.toJSONString(list);
		}
		logger.debug(String.format("user uid :%s order list isEmpty !", uid));
		return "{}";
	}

	@RequestMapping("/info")
	public String info(String orderNo) {

		OrderInfo order = orderInfo.getByOrderNo(orderNo);
		if (null != order) {
		}
		return order.toString();
	}

	@RequestMapping("/test")
	public String test(String id) {

		long sellerId = StringUtils.parseToLong(id, 0);
		OrderInfo order = orderInfoXmlService.getOrderInfoBySellerId(sellerId);
		if (null != order) {
		}
		return order.toString();
	}

}
