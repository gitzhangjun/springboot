package com.pa.jk.sync.util;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.pa.jk.sync.bean.SyncTable;
import com.pa.jk.sync.controller.SyncSlaveController;
import com.pa.jk.sync.dao.SyncSlaveXmlMapper;

public class ThreadPoolUtil {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SyncSlaveController.class);
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(50, 100, 500L, TimeUnit.MINUTES,
			new LinkedBlockingQueue<Runnable>(5000), new ThreadPoolExecutor.DiscardOldestPolicy());

	public static boolean threadAdd(final SyncSlaveXmlMapper syncSlaveXmlMapper) {
		try {
			Callable<Boolean> call = new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					SyncTable table = new SyncTable();
					try {
						Random run = new Random();
						table.setPartner(run.nextInt(34645654));
						table.setSubject("姜某因非国家工作人员受贿罪被公安机关立案侦查");
						table.setBody(
								"2016年10月，廉正公署根据举报查实：区域大客户销售经理姜某，在某项目投标中，利用联想产品的折扣空间，向经销商索取贿赂人民币数万元。姜某的行为严重违反了联想关于禁止营私舞弊相关规定，联想已与姜某解除劳动关系。同时，姜某的行为涉嫌非国家工作人员受贿罪，已被公安机关立案侦查并刑事拘留。相关制度规定《联想中国平台纪律管理条例》有如下失职或营私舞弊行为，应当解除劳动关系：Ø给公司造成不良影响的Ø"
										+ "利用工作之便谋取私利，和/或索取、接受任何不正当利益，包括但不限于任何形式的财物或其他非财产利益《中华人民共和国刑法》第一百六十三条公司、企业或者其他单位的工作人员利用职务上的便利，索取他人财物或者非法收受他人财物，为他人谋取利益，数额较大的（注：人民币六万元），处五年以下有期徒刑或者拘役；数额巨大的（注：人民币一百万元），处五年以上有期徒刑，可以并处没收财产。廉正提示："
										+ "姜某作为区域大客户销售人员，在产品销售过程中，利用职务便利向经销商索取贿赂的行为，严重损害了联想的利益和声誉，扰乱了正常的商业秩序，是联想不能容忍且坚决打击的。联想产品的折扣，应该用于增加产品竞争力、提高客户对联想的认知和渠道的建设及营利。任何利用此类资源为个人及团队牟取私利的行为，都是违规甚至违法的。除上述姜某的行为外，也包括从渠道提取现金用作“团队小金库”等行为。根据相关司法解释，商业贿赂罪和职务侵占罪的定罪标准为人民币六万元。这是追究当事人刑事责任的唯一标准。联想将持续严肃查处内部贪腐行为，达到犯罪标准的将依法移交公安机关处理。");
						table.setTotalFee(run.nextDouble());
						SyncTable res = syncSlaveXmlMapper.save(table);
						logger.info("threadAdd>>>>" + res.getPartner());
						if (res != null) {
							return true;
						}
					} catch (Exception e) {
						logger.error("ThreadPoolUtil.threadAdd ERROR", e);
						throw new Exception(e.toString());
					}

					return false;
				}
			};
			threadPool.submit(call);
		} catch (Exception e) {
			logger.error("sync add Favorites error", e);
		}
		return false;
	}
}
