package com.pa.jk.pay.util;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExcepFactor implements Serializable {

	private static final long serialVersionUID = 4826765296261100979L;
	// 系统级异常
	public static final int ERROR_LEVEL_SYSTEM = 1;
	// 服务级异常
	public static final int ERROR_LEVEL_SERVICE = 2;

	private static final Set<ExcepFactor> excepFactors = new HashSet<ExcepFactor>();

	/** 默认错误 */
	public static final ExcepFactor E_DEFAULT = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.INTERNAL_SERVER_ERROR,
			1, "system error!", "系统内部错误!");

	/** 服务端资源不可用 */
	public static final ExcepFactor E_SERVICE_UNAVAILABLE = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.SERVICE_UNAVAILABLE, 2, "service unavailable!", "服务端资源不可用!");

	public static final ExcepFactor E_DEPENDENCE_SERVICE_UNAVAILABLE = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.SERVICE_UNAVAILABLE, 3, "dependence service unavailable!", "依赖服务不可用!");

	// http协议以及参数类错误

	/** 接口不存在 */
	public static final ExcepFactor E_API_NOT_EXIST = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.NOT_FOUND, 10,
			"Request Api not found!", "接口不存在!");

	/** Http 方法错误 */
	public static final ExcepFactor E_METHOD_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.METHOD_NOT_ALLOWED, 11, "HTTP METHOD is not suported for this request!", "请求的HTTP METHOD不支持!");

	/**
	 * 接口已经废弃
	 */
	public static final ExcepFactor E_API_DEPRECATED_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.SERVICE_UNAVAILABLE, 12, "api is deprecated.", "该接口已经废弃");

	public static final ExcepFactor E_UNSUPPORT_MEDIATYPE_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.UNSUPPORTED_MEDIA_TYPE, 13, "unsupport mediatype (%s)", "不支持的 MediaType (%s).");

	public static final ExcepFactor E_PARAM_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.BAD_REQUEST, 14,
			"param error, see doc for more info.", "错误:参数错误，请参考API文档!");

	public static final ExcepFactor E_ILLEGAL_REQUEST = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.BAD_REQUEST,
			15, "Illegal Request!", "非法请求！");

	public static final ExcepFactor E_PARAM_MISS_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.BAD_REQUEST,
			16, "miss required parameter (%s), see doc for more info.", "错误:缺失必选参数:%s，请参考API文档.");

	public static final ExcepFactor E_PARAM_INVALID_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.BAD_REQUEST, 17,
			"parameter (%s)'s value invalid,expect (%s), but get (%s), see doc for more info.",
			"错误:参数值非法,希望得到 (%s),实际得到 (%s)，请参考API文档.");

	public static final ExcepFactor E_POST_BODY_LENGTH_LIMIT = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.BAD_REQUEST, 18, "request boday length over limit.", "请求长度超过限制!");

	/** 图片格式不对 */
	public static final ExcepFactor E_INPUT_IMAGEERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.BAD_REQUEST,
			19, "unsupported image type, only suport JPG, GIF, PNG!", "不支持的图片类型,仅仅支持JPG,GIF,PNG!");

	/** 图片太大 */
	public static final ExcepFactor E_INPUT_IMAGESIZEERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.BAD_REQUEST, 20, "image size too large.", "图片太大。");

	/** 限制类错误 **/

	/** IP限制，不能请求该资源 */
	public static final ExcepFactor E_IP_LIMIT = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.BAD_REQUEST, 30,
			"IP limit!", "IP限制，不能请求该资源!");

	/** 来源级别错误 */
	public static final ExcepFactor E_SOURCE_LEVEL_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.FORBIDDEN,
			31, "permission denied! Need a high level appkey!", "该资源需要appkey拥有更高级的授权!");

	/** 来源不对 */
	public static final ExcepFactor E_SOURCE_ERROR = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.BAD_REQUEST, 32,
			"source paramter(appkey) is missing", "缺少 source参数(appkey)!");

	/** 用户IP次数达到限制 */
	public static final ExcepFactor E_IP_OUTOFLIMIT = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.FORBIDDEN, 33,
			"IP requests out of rate limit!", "IP请求超过上限!");

	/** 用户请求次数达到限制 */
	public static final ExcepFactor E_USER_OUTOFLIMIT = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.FORBIDDEN, 34,
			"User requests out of rate limit!", "用户请求超过上限!");

	/** 用户对特定接口的请求次数达到限制 */
	public static final ExcepFactor E_API_OUTOFLIMIT = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.FORBIDDEN, 35,
			"User requests for %s out of rate limit!", "用户请求接口%s超过上限!");

	/** 非法用户 */
	public static final ExcepFactor E_USER_NOTOPEN = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0, HttpStatus.FORBIDDEN, 50,
			"invalid user!", "不合法的用户!");

	/** 非法请求 */
	public static final ExcepFactor E_REQUEST_NOT_INTERNEL = new ExcepFactor(ERROR_LEVEL_SYSTEM, 0,
			HttpStatus.FORBIDDEN, 60, "Illegal request, allowing only internel network access!", "非法请求，只允许内网访问!");

	private final HttpStatus httpStatus;
	private final int level;
	private final int serviceId;
	private final int errorCode;
	private final String errorMsg;
	private final String errorMsgCn;

	private ExcepFactor(int level, int serviceId, HttpStatus httpStatus, int errorCode, String errorMsg,
			String errorMsgCn) {

		if (errorCode < 0 || errorCode > 99) {
			throw new IllegalArgumentException("errorCode must between 1~99 .");
		}
		if (serviceId < 0 || serviceId > 99) {
			throw new IllegalArgumentException("serviceId must between 1~99 .");
		}
		this.level = level;
		this.serviceId = serviceId;
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorMsgCn = errorMsgCn;
		if (excepFactors.contains(this)) {
			throw new IllegalArgumentException("this error exist: " + this.getErrorCode());
		}
		excepFactors.add(this);
	}

	protected ExcepFactor(int serviceId, HttpStatus httpStatus, int errorCode, String errorMsg, String errorMsgCn) {
		this(ERROR_LEVEL_SERVICE, serviceId, httpStatus, errorCode, errorMsg, errorMsgCn);
	}

	public HttpStatus getStatus() {
		return httpStatus;
	}

	public int getErrorCode() {
		return this.level * 10000 + this.serviceId * 100 + this.errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getErrorMsg(Object... args) {
		if (args == null || args.length == 0) {
			return errorMsg;
		}
		return String.format(errorMsg, args);
	}

	public String getErrorMsgCn() {
		return errorMsgCn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getErrorCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ExcepFactor other = (ExcepFactor) obj;
		if (this.getErrorCode() != other.getErrorCode()) {
			return false;
		}
		return true;
	}

	public String toString() {
		return String.format("%s\t%s\t%s", this.getErrorCode(), this.getErrorMsg(), this.getErrorMsgCn());
	}

	public static void main(String[] args) {
		printException(new PrintWriter(System.out));
	}

	public static void printException(PrintWriter out) {
		List<ExcepFactor> excepList = new ArrayList<ExcepFactor>(excepFactors);
		Collections.sort(excepList, new Comparator<ExcepFactor>() {
			@Override
			public int compare(ExcepFactor o1, ExcepFactor o2) {
				return o1.getErrorCode() - o2.getErrorCode();
			}

		});
		for (ExcepFactor e : excepList) {
			out.println(e.toString());
			out.flush();
		}
	}
}
