package com.pa.jk.sync.bean;

import java.util.Date;

/**
 * 
 * CREATE TABLE club.`sync_table` (
 * 
 * `ID` BIGINT(16) primary key not null auto_increment,
 * 
 * `PARTNER` BIGINT(16) NOT NULL,
 * 
 * `SUBJECT` varchar(500) DEFAULT NULL,
 * 
 * `BODY` varchar(5000) DEFAULT NULL,
 * 
 * `TOTAL_FEE` decimal(8,2) DEFAULT NULL,
 * 
 * `CTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 * 
 * KEY `PARTNER` (`PARTNER`)
 * 
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 
 * @author zhangjun19
 *
 */
public class SyncTable {
	private int id;
	private int partner;
	private String subject;
	private String body;
	private double totalFee;
	private Date ctime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPartner() {
		return partner;
	}

	public void setPartner(int partner) {
		this.partner = partner;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
