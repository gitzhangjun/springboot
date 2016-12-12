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

import com.pa.jk.pay.bean.TransactionDetails;

@Mapper
public interface TransactionDetailsMapper {

	@Insert("INSERT INTO  `TRANSACTION_DETAILS`(NOTIFY_ID, TRADE_NO,BIZ_ID , ORDER_NO , BUYER_ID,BUYER_LOGON_ID,SELLER_ID,SELLER_EMAIL,TRADE_STATUS,TOTAL_AMOUNT,RECEIPT_AMOUNT,"
			+ "INVOICE_AMOUNT,BUYER_PAY_AMOUNT,POINT_AMOUNT,REFUND_FEE,SUBJECT,BODY,GMT_CREATE,GMT_PAYMENT,GMT_REFUND,GMT_CLOSE,FUND_BILL_LIST)  "
			+ "VALUES(#{notifyId}, #{tradeNo}, #{outBizNo}, #{orderNo}, #{buyerId}, #{buyerLogonId}, "
			+ "#{sellerId}, #{sellerEmail}, #{tradeStatus}, #{totalAmount}, #{receiptAmount}, #{invoiceAmount}, #{buyerPayAmount}, #{pointAmount}, #{refundFee}, #{subject}"
			+ ", #{body}, #{gmtCreate}, #{gmtPayment}, #{gmtRefund}, #{gmtClose}, #{fundBillList})")
	void save(TransactionDetails transactionDetails);

	@Select("select * from `TRANSACTION_DETAILS` where NOTIFY_ID = #{notifyId}")
	@Results(value = {
			@Result(property = "notifyId", column = "NOTIFY_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "tradeNo", column = "TRADE_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "outBizNo", column = "BIZ_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "orderNo", column = "ORDER_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerId", column = "BUYER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerLogonId", column = "BUYER_LOGON_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "sellerId", column = "SELLER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "sellerEmail", column = "SELLER_EMAIL", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "tradeStatus", column = "TRADE_STATUS", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "totalAmount", column = "TOTAL_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "receiptAmount", column = "RECEIPT_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "invoiceAmount", column = "INVOICE_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "buyerPayAmount", column = "BUYER_PAY_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "pointAmount", column = "POINT_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "refundFee", column = "REFUND_FEE", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subject", column = "SUBJECT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "body", column = "BODY", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "gmtCreate", column = "GMT_CREATE", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtPayment", column = "GMT_PAYMENT", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtRefund", column = "GMT_REFUND", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtClose", column = "GMT_CLOSE", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "fundBillList", column = "FUND_BILL_LIST", javaType = Double.class, jdbcType = JdbcType.DECIMAL) })
	TransactionDetails getByNotifyId(@Param("notifyId") String notifyId);

	@Select("select * from  `TRANSACTION_DETAILS` where ORDER_NO = #{ORDER_NO}")
	@Results(value = {
			@Result(property = "notifyId", column = "NOTIFY_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "tradeNo", column = "TRADE_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "outBizNo", column = "BIZ_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "orderNo", column = "ORDER_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerId", column = "BUYER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "buyerLogonId", column = "BUYER_LOGON_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "sellerId", column = "SELLER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "sellerEmail", column = "SELLER_EMAIL", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "tradeStatus", column = "TRADE_STATUS", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "totalAmount", column = "TOTAL_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "receiptAmount", column = "RECEIPT_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "invoiceAmount", column = "INVOICE_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "buyerPayAmount", column = "BUYER_PAY_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "pointAmount", column = "POINT_AMOUNT", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "refundFee", column = "REFUND_FEE", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subject", column = "SUBJECT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "body", column = "BODY", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "gmtCreate", column = "GMT_CREATE", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtPayment", column = "GMT_PAYMENT", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtRefund", column = "GMT_REFUND", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "gmtClose", column = "GMT_CLOSE", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "fundBillList", column = "FUND_BILL_LIST", javaType = Double.class, jdbcType = JdbcType.DECIMAL) })
	List<TransactionDetails> getByOrderNo(@Param("ORDER_NO") String ORDER_NO);
}
