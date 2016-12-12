package com.pa.jk.pay.util;

public class StringUtils {

	public static boolean isBlank(CharSequence cs) {
		// 标记字符长度，
		int strLen;
		// 字符串不存在或者长度为0
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			// 判断空格，回车，换行等，如果有一个不是上述字符，就返回false
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static double parseToDouble(String value, double def) {
		if (!isBlank(value)) {
			try {
				return Double.parseDouble(value);
			} catch (Exception e) {
				return def;
			}
		}
		return def;
	}

	public static long parseToLong(String value, long def) {
		if (!isBlank(value)) {
			try {
				return Long.parseLong(value);
			} catch (Exception e) {
				return def;
			}
		}
		return def;
	}

}
