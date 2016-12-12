package com.pa.jk.pay.remote.parameter;

import java.util.Date;

import com.pa.jk.pay.config.AlipayConfig;
import com.pa.jk.pay.util.DateUtils;

/**
 * 公共请求参数
 * 
 * @author zhangjun19
 *
 */
public abstract class BaseReqParas {


	
	public final static String app_id = AlipayConfig.APPID;// String 32
															// 支付宝分配给开发者的应用ID
															// 2014072300007148
	public final static String private_key = AlipayConfig.private_key;

	public final static String alipay_public_key = AlipayConfig.alipay_public_key;

	public final static String format = "JSON";// String 40 仅支持JSON JSON
	public final static String charset = AlipayConfig.input_charset;// String 10
																	// 请求使用的编码格式，如utf-8,gbk,gb2312等
	public final static String sign_type = AlipayConfig.sign_type;// String 10
	// 商户生成签名字符串所使用的签名算法类型，目前支持RSA RSA
	public final static String timestamp = DateUtils.formateDateTime(new Date());// String
																					// 发送请求的时间，格式"yyyy-MM-dd
																					// HH:mm:ss"
																					// 2014-07-24
																					// 03:07:50
	public final static String version = "1.0";// String 3 调用的接口版本，固定为：1.0

}
