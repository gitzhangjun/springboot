package com.pa.jk.pay.util;

import java.util.UUID;

//生成ID
public class UUIDUtil {
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		// 得到对象产生的ID
		String id = uuid.toString();
		// 转换为大写
		id = id.toUpperCase();
		return id;
	}
}
