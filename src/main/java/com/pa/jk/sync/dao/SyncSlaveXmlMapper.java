package com.pa.jk.sync.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.pa.jk.sync.bean.SyncTable;

/**
 * XML 配置方式
 * 
 * @author zhangjun19
 *
 */
@Component
public class SyncSlaveXmlMapper {

	@Resource(name = "syncSqlSessionTemplate")
	private SqlSessionTemplate syncSqlSessionTemplate;

	public SyncTable save(SyncTable table) {
		int res = this.syncSqlSessionTemplate.insert("save", table);
		if (res > 0) {
			return table;
		}
		return null;
	}
}
