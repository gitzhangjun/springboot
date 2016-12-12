package com.pa.jk.pay.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.pa.jk.pay.bean.OrderInfo;

@Mapper
public interface OrderInfoMapper {

	@Select("select * from `ORDER_INFO` where UID = #{uid}")
	@Results(value = { @Result(property = "uid", column = "UID", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "sellerId", column = "SELLER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "orderNo", column = "ORDER_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subject", column = "SUBJECT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "body", column = "BODY", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "STATUS", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "totalFee", column = "TOTAL_FEE", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "ctime", column = "CTIME", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
	List<OrderInfo> getByUid(@Param("uid") long uid);

	@Insert("INSERT INTO `ORDER_INFO`(UID,PARTNER, SELLER_ID,ORDER_NO,SUBJECT,BODY,TOTAL_FEE,STATUS)  VALUES(#{uid},#{partner}, #{sellerId}, #{orderNo}, #{subject}, #{body},#{totalFee}, #{status})")
	void save(OrderInfo orderInfo);

	@Select("select * from  `ORDER_INFO` where ORDER_NO = #{ORDER_NO}")
	@Results(value = { @Result(property = "uid", column = "UID", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "sellerId", column = "SELLER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "orderNo", column = "ORDER_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subject", column = "SUBJECT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "body", column = "BODY", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "STATUS", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "totalFee", column = "TOTAL_FEE", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "ctime", column = "CTIME", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
	OrderInfo getByOrderNo(@Param("ORDER_NO") String orderNo);

	/**
	 * 根据订单号修改订单状态
	 * 
	 * @param ORDER_NO
	 * @param STATUS
	 * @return
	 */
	@Update("update `ORDER_INFO`  SET  status=#{STATUS} where ORDER_NO = #{ORDER_NO} ")
	@Results(value = { @Result(property = "uid", column = "UID", javaType = Long.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "sellerId", column = "SELLER_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "orderNo", column = "ORDER_NO", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subject", column = "SUBJECT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "body", column = "BODY", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "status", column = "STATUS", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "totalFee", column = "TOTAL_FEE", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
			@Result(property = "ctime", column = "CTIME", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
	int updateStatusByOrderNo(@Param("ORDER_NO") String orderNo, @Param("STATUS") byte status);

}
