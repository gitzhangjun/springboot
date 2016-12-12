package com.pa.jk.pay.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pa.jk.pay.util.DateUtils;

/**
 * 支付宝-交易详情
 * TIPS：out_biz_no、buyer_logon_id、seller_email、receipt_amount、invoice_amount、
 * buyer_pay_amount、point_amount、fund_bill_list参数暂时不会返回，预计在2016年11月中旬即将开放。
 * 
 * @author zhangjun19
 *
 */
public class TransactionDetails {
	private int id;
	private String notifyTme; // Date 通知时间
	private String notifyType;// String(64) 通知类型
	private String notifyId;// String(128) 通知校验ID
	private String signType;// String(10) 签名类型
	private String sign;// String(256) 签名
	private String tradeNo;// 支付宝交易号
	private String orderNo;// String(64) 商户订单号 原支付请求的商户订单号
	private String outBizNo; // String(64) 商户业务号 商户业务ID，主要是退款通知中返回退款申请的流水号
	private String buyerId;// String(16) 买家支付宝用户号
							// 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字
	private String buyerLogonId;// String(100) 买家支付宝账号
	private String sellerId;// 卖家支付宝用户号 String(30) 卖家支付宝用户号
	private String sellerEmail;// 卖家支付宝账号 String(100) 卖家支付宝账号
	private String tradeStatus;// 交易状态 String(32)
	private String tradeCode;// 交易状态 byte(2)
	private double totalAmount;// 订单金额 Number(9,2) 本次交易支付的订单金额，单位为人民币（元）
	private double receiptAmount;// 实收金额 Number(9,2)
	private double invoiceAmount;// 开票金额 Number(9,2) 用户在交易中支付的可开发票的金额
	private double buyerPayAmount;// 付款金额 Number(9,2) 用户在交易中支付的金额
	private double pointAmount;// 集分宝金额 Number(9,2) 使用集分宝支付的金额
	private double refundFee;// 总退款金额 Number(9,2) 退款通知中，返回总退款金额，单位为元，支持两位小数
	private String subject;// 订单标题 String(256)
							// 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来
	private String body;// 商品描述 String(400) 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来
	private Date gmtCreate;// 交易创建时间 Date 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss
	private Date gmtPayment;;// 交易付款时间 Date 该笔交易的买家付款时间。格式为yyyy-MM-dd
								// HH:mm:ss
	private Date gmtRefund; // 交易退款时间 Date 该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S
	private Date gmtClose;// 交易结束时间 Date 该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss
	private String fundBillList;// 支付金额信息 String(512) 支付成功的各个渠道金额信息
	private byte fundBillCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotifyTme() {
		return notifyTme;
	}

	public void setNotifyTme(String notifyTme) {
		this.notifyTme = notifyTme;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

	public String getOutBizNo() {
		return outBizNo;
	}

	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerLogonId() {
		return buyerLogonId;
	}

	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public double getBuyerPayAmount() {
		return buyerPayAmount;
	}

	public void setBuyerPayAmount(double buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}

	public double getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(double pointAmount) {
		this.pointAmount = pointAmount;
	}

	public double getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(double refundFee) {
		this.refundFee = refundFee;
	}

	public String getSubject() {
		return subject;
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

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtPayment() {
		return gmtPayment;
	}

	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	public Date getGmtRefund() {
		return gmtRefund;
	}

	public void setGmtRefund(Date gmtRefund) {
		this.gmtRefund = gmtRefund;
	}

	public Date getGmtClose() {
		return gmtClose;
	}

	public void setGmtClose(Date gmtClose) {
		this.gmtClose = gmtClose;
	}

	public String getFundBillList() {
		return fundBillList;
	}

	public void setFundBillList(String fundBillList) {
		this.fundBillList = fundBillList;
	}

	public byte getFundBillCode() {
		return fundBillCode;
	}

	public void setFundBillCode(byte fundBillCode) {
		this.fundBillCode = fundBillCode;
	}

	/**
	 * 支付状态
	 * 
	 * @author zhangjun19
	 */
	public static enum TradeStatus {
		WAIT_BUYER_PAY("WAIT_BUYER_PAY", (byte) 1), // 1交易创建，等待买家付款
		TRADE_CLOSED("TRADE_CLOSED", (byte) 2), // 2未付款交易超时关闭或支付完成后全额退款
		TRADE_SUCCESS("TRADE_SUCCESS", (byte) 3), // 3交易支付成功
		TRADE_FINISHED("TRADE_FINISHED", (byte) 4);// 4交易结束，不可退款
		private byte value;
		private String descValue;

		private static Map<Byte, TradeStatus> valueTypes = new HashMap<Byte, TradeStatus>();
		private static Map<String, TradeStatus> descValueTypes = new HashMap<String, TradeStatus>();

		static {
			for (TradeStatus t : TradeStatus.values()) {
				valueTypes.put(t.value, t);
				descValueTypes.put(t.descValue, t);
			}
		}

		public byte value() {
			return value;
		}

		private TradeStatus(String desc, byte v) {
			this.descValue = desc;
			this.value = v;
		}

		public static TradeStatus parseValue(byte value) {
			return valueTypes.get(value);
		}

		public static TradeStatus parseDescValue(String descValue) {
			if (com.pa.jk.pay.util.StringUtils.isBlank(descValue)) {
				return null;
			}
			return descValueTypes.get(descValue);
		}
	}

	/**
	 * 支付渠道
	 * 
	 * @author zhangjun19
	 */
	public static enum FundBillCode {

		COUPON("COUPON", (byte) 0), // 支付宝红包
		ALIPAYACCOUNT("ALIPAYACCOUNT", (byte) 1), // 支付宝余额
		POINT("POINT", (byte) 2), // 集分宝
		DISCOUNT("DISCOUNT", (byte) 3), // 折扣券
		PCARD("PCARD", (byte) 4), // 预付卡
		FINANCEACCOUNT("FINANCEACCOUNT", (byte) 5), // 余额宝
		MCARD("MCARD", (byte) 6), // 商家储值卡
		MDISCOUNT("MDISCOUNT", (byte) 7), // 商户优惠券
		MCOUPON("MCOUPON", (byte) 8), // 商户红包
		PCREDIT("PCREDIT", (byte) 9);// 蚂蚁花呗

		private byte value;
		private String descValue;

		private static Map<Byte, FundBillCode> valueTypes = new HashMap<Byte, FundBillCode>();
		private static Map<String, FundBillCode> descValueTypes = new HashMap<String, FundBillCode>();

		static {
			for (FundBillCode t : FundBillCode.values()) {
				valueTypes.put(t.value, t);
				descValueTypes.put(t.descValue, t);
			}
		}

		public byte value() {
			return value;
		}

		private FundBillCode(String desc, byte v) {
			this.descValue = desc;
			this.value = v;
		}

		public static FundBillCode parseValue(byte value) {
			return valueTypes.get(value);
		}

		public static FundBillCode parseDescValue(String descValue) {
			if (com.pa.jk.pay.util.StringUtils.isBlank(descValue)) {
				return null;
			}
			return descValueTypes.get(descValue);
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer(256);
		str.append(" notifyTme:").append(notifyTme).append(" notifyType:").append(notifyType).append(" notifyId:")
				.append(notifyId).append(" signType:").append(signType).append(" sign:").append(sign)
				.append(" tradeNo:").append(tradeNo).append(" orderNo:").append(orderNo).append(" outBizNo:")
				.append("\n").append(outBizNo).append(" buyerId:").append(buyerId).append(" buyerLogonId:")
				.append(buyerLogonId).append(" sellerId:").append(sellerId).append(" sellerEmail:").append(sellerEmail)
				.append(" tradeStatus:").append(tradeStatus).append(" tradeCode:").append(tradeCode)
				.append(" totalAmount:").append(totalAmount).append("\n").append(" receiptAmount:")
				.append(receiptAmount).append(" invoiceAmount:").append(invoiceAmount).append(" buyerPayAmount:")
				.append(buyerPayAmount).append(" pointAmount:").append(pointAmount).append(" refundFee:")
				.append(refundFee).append(" subject:").append(subject).append(" body:").append(body)
				.append(" gmtCreate:").append(DateUtils.formateDateTime(gmtCreate)).append("\n").append(" gmtPayment:")
				.append(DateUtils.formateDateTime(gmtPayment)).append(" gmtRefund:")
				.append(DateUtils.formateDateTime(gmtRefund)).append(" gmtClose:")
				.append(DateUtils.formateDateTime(gmtClose)).append(" fundBillList:").append(fundBillList)
				.append(" fundBillCode:").append(fundBillCode);
		return str.toString();
	}
}
