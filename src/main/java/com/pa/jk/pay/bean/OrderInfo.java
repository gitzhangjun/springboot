package com.pa.jk.pay.bean;

import java.io.Serializable;
import java.util.Date;

import com.pa.jk.pay.bean.TransactionDetails.TradeStatus;
import com.pa.jk.pay.config.AlipayConfig;
import com.pa.jk.pay.util.protostuff.obj.Entity;

/**
 * 订单基本信息
 * 
 * @author zhangjun19
 *
 */
public class OrderInfo implements Serializable,Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1549745352454329040L;
	private int id;
	private long partner;
	private long uid;
	private String sellerId;
	private String orderNo;
	private String subject;
	private String body;
	private double totalFee;
	private TradeStatus status;
	private Date ctime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPartner() {
		return partner;
	}

	public void setPartner(long partner) {
		this.partner = partner;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSubject() {
		return subject;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = TradeStatus.parseValue(status);
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public OrderInfo() {
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public OrderInfo(long uid, String orderNo, String subject, String body, double totalFee) {
		this.uid = uid;
		this.sellerId = AlipayConfig.SELLER;
		this.partner = Long.parseLong(AlipayConfig.partner);
		this.totalFee = totalFee;
		this.orderNo = orderNo;
		this.body = body;
		this.ctime = new Date();
		this.subject = subject;
		this.status = TradeStatus.WAIT_BUYER_PAY;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer(256);
		str.append("{ \"id\":\"").append(id).append("\" ,\"uid\":\"").append(uid).append("\" ,\"partner\":\"")
				.append(partner).append("\" ,\"sellerId\":\"").append(sellerId).append("\", \"orderNo\":\"")
				.append(orderNo).append("\" ,\"subject\":\"").append(subject).append("\",\"body\":\"").append(body)
				.append("\",\"totalFee\":\"").append(totalFee).append("\",\"ctime\":\"").append(ctime).append("\"}");
		return str.toString();
	}
}
