package com.pa.jk.pay.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.pa.jk.pay.bean.TradeRefund;

@Mapper
public interface TradeRefundMapper {

	@Select("select * from `TRADE_REFUND` where TRADE_NO = #{TRADE_NO}")
	@Results(value = {
			@Result(property = "tradeNo", column = "TRADE_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "orderNo", column = "ORDER_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerLogonId", column = "BUYER_LOGON_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "fundChange", column = "FUND_CHANGE", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "refundFee", column = "REFUND_FEE", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "ctime", column = "LOCAL_CTIME", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtRefundPay", column = "GMT_REFUND_PAY", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "refundDetailItemList", column = "REFUND_DETAIL_ITEM_LIST", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "storeName", column = "STORE_NAME", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerUserId", column = "BUYER_USER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "sendBackFee", column = "SEND_BACK_FEE", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "refundReason", column = "REFUND_REASON", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "operatorId", column = "OPERATOR_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	List<TradeRefund> getOrderInfoByTradeNo(@Param("TRADE_NO") String TRADE_NO);

	@Insert("INSERT INTO  `TRADE_REFUND`(TRADE_NO, ORDER_NO,BUYER_LOGON_ID,FUND_CHANGE,REFUND_FEE,LOCAL_CTIME,GMT_REFUND_PAY,REFUND_DETAIL_ITEM_LIST,STORE_NAME,BUYER_USER_ID,SEND_BACK_FEE,REFUND_REASON,OPERATOR_ID) "
			+ " VALUES(#{tradeNo}, #{orderNo}, #{buyerLogonId}, #{fundChange}, #{refundFee}, #{ctime}, #{gmtRefundPay}, #{refundDetailItemList}, #{storeName}, #{buyerUserId}, #{sendBackFee},#{refundReason},#{operatorId})")
	void save(TradeRefund tradeRefund);

	@Select("select * from `TRADE_REFUND` where ORDER_NO = #{ORDER_NO}")
	@Results(value = {
			@Result(property = "tradeNo", column = "TRADE_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "orderNo", column = "ORDER_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerLogonId", column = "BUYER_LOGON_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "fundChange", column = "FUND_CHANGE", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "refundFee", column = "REFUND_FEE", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "ctime", column = "LOCAL_CTIME", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtRefundPay", column = "GMT_REFUND_PAY", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "refundDetailItemList", column = "REFUND_DETAIL_ITEM_LIST", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "storeName", column = "STORE_NAME", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerUserId", column = "BUYER_USER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "sendBackFee", column = "SEND_BACK_FEE", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "refundReason", column = "REFUND_REASON", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "operatorId", column = "OPERATOR_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	TradeRefund getOrderInfoByOrderNo(@Param("ORDER_NO") String ORDER_NO);
}
