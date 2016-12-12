package com.pa.jk.pay.remote;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.pa.jk.pay.remote.parameter.BaseReqParas;
import com.pa.jk.pay.remote.parameter.ReqParas;

/**
 * 统一收单线下交易查询
 * 
 * @author zhangjun19
 *
 */
@Service("remoteTradeQueryService")
public class RemoteTradeQueryService {
	public final static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RemoteTradeQueryService.class);

	public AlipayResponse execute(ReqParas reqParas) {

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				BaseReqParas.app_id, BaseReqParas.private_key, BaseReqParas.format, BaseReqParas.charset,
				BaseReqParas.alipay_public_key);

		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

		request.setBizContent(reqParas.toString());

		AlipayTradeQueryResponse response = null;

		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			logger.error("AlipayTradeQuery err", e);
		}
		return response;
	}

}
