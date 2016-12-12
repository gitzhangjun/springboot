package com.pa.jk.pay.remote.parameter;

/**
 * 统一收单交易退款查询 参数
 * 
 * @author zhangjun19
 *
 */
public class TradeRefundQueryParas implements ReqParas {

	private String tradeNo;// String 特殊可选 64 支付宝交易号，和商户订单号不能同时为空
							// 20150320010101001
	private String orderNo;// String 特殊可选 64 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
							// trade_no,out_trade_no如果同时存在优先取trade_no
							// 2014112611001004680073956707
	private String outRequestNo;// String 必须 64
								// 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
								// 2014112611001004680073956707

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		StringBuffer json = new StringBuffer(256);
		json.append("{\"out_trade_no\":\"").append(orderNo).append("\",").append("\"trade_no\":\"").append(tradeNo)
				.append("\",").append("\"out_request_no\":\"").append(outRequestNo).append("\",").append("}");
		
		return json.toString();
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

	public String getOutRequestNo() {
		return outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toJson();
	}
}
