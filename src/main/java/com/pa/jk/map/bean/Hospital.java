package com.pa.jk.map.bean;

/**
 * 医院基本信息
 * 
 * 
 * 经纬度的获取是通过医院中文名字调取百度地图api，如果调取后发现准确度小于75 ，用医院地址重新获取经纬度
 * 最后比较两次获取的准确度，取准确度高的经纬度，
 * @author zhangjun19
 *
 */
public class Hospital {

	private int id;//
	private long partnerId;//
	private long hospitalId; // 医院ID
	private String cnName; // 中文名字
	private String enName; //
	private String cnAddress; //中文地址
	private String enAddress; //
	private int countryCode; // 国家编码
	private int provinceCode;// 省份编码
	private int cityCode;// 市编码
	private String regionCode;// 地区编码 【组合形式 国家,省份,市】
	private String regionName;// 地区名 【组合形式 国家,省份,市】
	private String longitude; // 经度 【用的是百度map v2.0版坐标】
	private String latitude; // 纬度 【用的是百度map v2.0版坐标】
	private int confidence;// 精确度 数值越大，准确度越高 【 -1 表示不可用，需要手工调整数据】

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnAddress() {
		return cnAddress;
	}

	public void setCnAddress(String cnAddress) {
		this.cnAddress = cnAddress;
	}

	public String getEnAddress() {
		return enAddress;
	}

	public void setEnAddress(String enAddress) {
		this.enAddress = enAddress;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public int getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getConfidence() {
		return confidence;
	}

	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}

}
