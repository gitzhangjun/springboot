package com.pa.jk.pay.remote.parameter;

/**
 * 统一收单线下交易查询 参数
 * 
 * @author zhangjun19
 *
 */
public class TradeQueryParas implements ReqParas {

	private String orderNo;// 特殊可选 64 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
							// trade_no,out_trade_no如果同时存在优先取trade_no
							// 20150320010101001
	private String tradeNo;// String 特殊可选 64 支付宝交易号，和商户订单号不能同时为空
							// 2014112611001004680 073956707

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		StringBuffer json = new StringBuffer(256);
		json.append("{\"out_trade_no\":\"").append(orderNo).append("\",").append("\"trade_no\":\"").append(tradeNo)
				.append("\",").append("}");
		return json.toString();
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toJson();
	}
}
