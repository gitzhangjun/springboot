package com.test.pa.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	//合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String partner = "2088522042602384";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALNs/aEUV6SfCu9Eeh8meB5dFNobP9hcp6DkQBKygiFU0cU2z0rjgSiWOI25GEZThp+l/xVt8tBiJ3d/zYb/jOXWANRYF2KsO1qSCzV45sKOw7Q6dTJxS35dPcP2vO7R6fIOOPDbKA50Y3nSIau1wUwxX0xIkLYd/cezagJ0Kc2VAgMBAAECgYEArLlCG3uKt1XHX/gKm8wbnBc9CI1s06q2T+MKbzVaoh2V5oTR3qTN5DVgccX6QVEM6skQmx+BvOqQVbA5OTju94EsXaGajE3dKEEkCi5RwFrszoa0uyjYrVpE2sqhl6n+NmiJLau5M0J4/+kE6T9b6l4iYlp+ebZX2jH9HjiPNyECQQDe7FgoyWBNzjqtVuaaNf+1p0WXUK3g12uTAuxwib01TLVBD2EuCZea+PGMg+G3oDExCHdjErYEe5td9oHsARE9AkEAzgxpM+SwwvaoZjXpYDCpvGXPlUvpPjk8Lehql3josg1GUWDFMLytJsvrydXLC+7jQ4QzD7KYorgmwD7/dLJDOQJBAIVgLQMCMV978zmA0+oSQtNBqG53NAFhghhsWGCg2JbKNWwesosj7BQpvAPRaDxAWQ44PY4GROb7uaKHeYZlwRUCQEKjGubwgAYdlWqw5s/4DPzkBFVpXn6ELiU+tDJWpRLoXiR1YnCXpHwWmSQGY4txb8hnGchq3xhI7CdieGV6PWkCQQC9ErR8Yr2FZfPNm9q4rbuVLJkppGDm4ididzMKCpYPJdgekTz3TO93NDtY/O/eb2xUM1fpHP8OBvpEjwJci9tc";
	
	//支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzbP2hFFeknwrvRHofJngeXRTaGz/YXKeg5EASsoIhVNHFNs9K44EoljiNuRhGU4afpf8VbfLQYid3f82G/4zl1gDUWBdirDtakgs1eObCjsO0OnUycUt+XT3D9rzu0enyDjjw2ygOdGN50iGrtcFMMV9MSJC2Hf3Hs2oCdCnNlQIDAQAB";
	
	
	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path ="C://";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 接收通知的接口名
	public static String service = "mobile.securitypay.pay";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}

