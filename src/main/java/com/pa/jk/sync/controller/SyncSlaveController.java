package com.pa.jk.sync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa.jk.sync.bean.SyncTable;
import com.pa.jk.sync.dao.SyncSlaveXmlMapper;
import com.pa.jk.sync.util.ThreadPoolUtil;

@RestController
@RequestMapping("/sync")
public class SyncSlaveController {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SyncSlaveController.class);
	@Autowired
	private SyncSlaveXmlMapper syncSlaveXmlMapper;

	@RequestMapping("/add")
	public String add(int partner, String subject, String body, double totalFee) {
		SyncTable table = new SyncTable();
		table.setPartner(partner);
		table.setSubject(subject);
		table.setBody(body);
		table.setTotalFee(totalFee);

		SyncTable res = syncSlaveXmlMapper.save(table);
		System.out.println(res.getBody());
		return res.getBody();
	}

	@RequestMapping("/pressure")
	public String pressure(int threadNumber) {
		logger.info("Test>>>>>>>>>>>>>>>>>");
		for (int i = 0; i < threadNumber; i++) {
			ThreadPoolUtil.threadAdd(syncSlaveXmlMapper);
			// ThreadPoolUtil pool = new ThreadPoolUtil();
			// pool.threadAdd(syncSlaveXmlMapper);
		}
		return "test";
	}

}
