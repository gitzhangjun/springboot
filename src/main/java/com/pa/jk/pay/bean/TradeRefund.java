package com.pa.jk.pay.bean;

import java.util.Date;

/**
 * 退款记录
 * 
 * @author zhangjun19
 *
 */
public class TradeRefund {

	private String id;// 本地流水号
	private String tradeNo;// String 必填 64 2013112011001004330000121536 支付宝交易号
	private String orderNo;// String 必填 64 商户订单号 6823789339978248
	private String buyerLogonId;// String 必填 100 用户的登录id 159****5620
	private String fundChange;// String 必填 1 本次退款是否发生了资金变化 Y
	private double refundFee;// Price 必填 11 退款总金额 88.88
	private Date ctime;// 本地创建时间
	private Date gmtRefundPay;// Date 必填 32 退款支付时间 2014-11-27 15:45:57
	private String refundDetailItemList;// TradeFundBill [] 选填 - 退款使用的资金渠道
	private String storeName;// String 选填 512 交易在支付时候的门店名称 望湘园联洋店
	private String buyerUserId;// String 必填 28 买家在支付宝的用户id 2088101117955611
	private String sendBackFee;// String 选填 11 本次商户实际退回金额 1.8
	private String refundReason;// String 可选 256 退款的原因说明 正常退款
	private String operatorId;// String 可选 30 商户的操作员编号 OP001

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBuyerLogonId() {
		return buyerLogonId;
	}

	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}

	public String getFundChange() {
		return fundChange;
	}

	public void setFundChange(String fundChange) {
		this.fundChange = fundChange;
	}

	public double getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(double refundFee) {
		this.refundFee = refundFee;
	}

	public Date getGmtRefundPay() {
		return gmtRefundPay;
	}

	public void setGmtRefundPay(Date gmtRefundPay) {
		this.gmtRefundPay = gmtRefundPay;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getRefundDetailItemList() {
		return refundDetailItemList;
	}

	public void setRefundDetailItemList(String refundDetailItemList) {
		this.refundDetailItemList = refundDetailItemList;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public String getSendBackFee() {
		return sendBackFee;
	}

	public void setSendBackFee(String sendBackFee) {
		this.sendBackFee = sendBackFee;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(512);
		str.append("{ \"id\":\"").append(id).append("\" ,\"tradeNo\":\"").append(tradeNo).append("\" ,\"orderNo\":\"")
				.append(orderNo).append("\" ,\"buyerLogonId\":\"").append(buyerLogonId).append("\", \"fundChange\":\"")
				.append(fundChange).append("\" ,\"refundFee\":\"").append(refundFee).append("\",\"ctime\":\"")
				.append(ctime).append("\",\"gmtRefundPay\":\"").append(gmtRefundPay)
				.append("\",\"refundDetailItemList\":\"").append(refundDetailItemList).append("\",\"storeName\":\"")
				.append(storeName).append("\",\"buyerUserId\":\"").append(buyerUserId).append("\",\"sendBackFee\":\"")
				.append(sendBackFee).append("\",\"refundReason\":\"").append(refundReason)
				.append("\",\"operatorId\":\"").append(operatorId).append("\"}");
		return str.toString();
	}

}
