package com.pa.jk.pay.remote;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.pa.jk.pay.remote.parameter.BaseReqParas;
import com.pa.jk.pay.remote.parameter.ReqParas;

/**
 * 统一收单交易退款接口
 * 
 * @author zhangjun19
 */
@Service("remoteTradeRefundService")
public class RemoteTradeRefundService {
	public final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RemoteTradeRefundService.class);

	public AlipayResponse execute(ReqParas reqParas) {

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				BaseReqParas.app_id, BaseReqParas.private_key, BaseReqParas.format, BaseReqParas.charset,
				BaseReqParas.alipay_public_key);

		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

		request.setBizContent(reqParas.toString());

		AlipayTradeRefundResponse response = null;

		try {
			response = alipayClient.execute(request);
			if (response.isSuccess()) {
				logger.info(">>>TradeRefund>>>>>调用成功>>>>>>");
			} else {
				logger.warn(">>>TradeRefund>>>>>调用失败>>>>>>");
			}
		} catch (AlipayApiException e) {
			logger.error("AlipayTradeRefund err", e);
		}
		return response;
	}

}
