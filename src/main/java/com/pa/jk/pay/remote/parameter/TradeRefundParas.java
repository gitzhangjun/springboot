package com.pa.jk.pay.remote.parameter;

/**
 * 退款 参数
 * 
 * @author zhangjun19
 *
 */
public class TradeRefundParas implements ReqParas {

	private String orderNo; // String 特殊可选 64 订单支付时传入的商户订单号,不能和
							// trade_no同时为空。 20150320010101001
	private String tradeNo;// String 特殊可选 64 支付宝交易号，和商户订单号不能同时为空
							// 2014112611001004680073956707
	private String refundAmount;// Price 必须 9 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
								// 200.12
	private String refundReason;// String 可选 256 退款的原因说明 正常退款
	private String outRequestNo;// String 可选 64
								// 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
								// HZ01RF001
	private String operatorId;// String 可选 30 商户的操作员编号 OP001
	private String storeId;// String 可选 32 商户的门店编号 NJ_S_001
	private String terminalId;// String 可选 32 商户的终端编号 NJ_T_001

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

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getOutRequestNo() {
		return outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		StringBuffer json = new StringBuffer(512);
		json.append("{\"out_trade_no\":\"").append(orderNo).append("\",").append("\"trade_no\":\"").append(tradeNo)
				.append("\",").append("\"refund_amount\":\"").append(refundAmount).append("\",")
				.append("\"refund_reason\":\"").append(refundReason).append("\",").append("\"out_request_no\":\"")
				.append(outRequestNo).append("\",").append("\"operator_id\":\"").append(operatorId).append("\",")
				.append("\"store_id\":\"").append(storeId).append("\",").append("\"terminal_id\":\"").append(terminalId)
				.append("}");
		return json.toString();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toJson();
	}
}
