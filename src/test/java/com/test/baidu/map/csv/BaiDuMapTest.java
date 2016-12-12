package com.test.baidu.map.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.google.gson.Gson;
import com.pa.jk.map.bean.Hospital;

public class BaiDuMapTest {

	private static SqlSessionFactory sessionFactory = null;
	static {
		// mybatis的配置文件
		String resource = "conf.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = BaiDuMapTest.class.getClassLoader().getResourceAsStream(resource);
		// 构建sqlSession的工厂
		sessionFactory = new SqlSessionFactoryBuilder().build(is);

	}

	/**
	 * 获取经纬度通过
	 * 
	 * @author jueyue 返回格式：Map<String,Object> map map.put("status",
	 *         reader.nextString());//状态 map.put("result", list);//查询结果 list<map
	 *         <String,String>> 密钥:f247cdb592eb43ebac6ccd27f796e2d2
	 */

	/**
	 * @param addr
	 *            查询的地址
	 * @return
	 * @throws IOException
	 */
	public String getCoordinate(String addr, String city) throws IOException {
		String address = null;
		String c = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
			c = java.net.URLEncoder.encode(city, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
//		String key = "shs86wBWD9IHkuGzMGmqxtmVQdt1jINV";
//		String key ="dguxmLPKgNWh9MfMDUecrwka7NTG6EYR";
		// String key = "E4805d16520de693a3fe707cdc962045";
		String key ="CrPUxifC2OFjOBf5G8f48RtOaNf4uOXr";
		String url = String.format("http://api.map.baidu.com/geocoder/v2/?ak=%s&output=json&address=%s&city=%s", key,
				address, c);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		InputStreamReader insr = null;
		BufferedReader br = null;
		StringBuffer json = new StringBuffer(256);

		try {

			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				br = new BufferedReader(insr);
				String data = null;
				while ((data = br.readLine()) != null) {
					json.append(data);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (insr != null) {
				insr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return json.toString();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// BaiDuMapTest baiDuMapTest = new BaiDuMapTest();
		// String json = baiDuMapTest.getCoordinate("北京市石景山区西黄村");
		// json = json.replaceAll(" ", "");
		// System.out.println(json);

		CSVFileUtil util = new CSVFileUtil("/home/zhangjun/tmp/ttt/apollo.csv");
		String line = util.readLine();
		int step = 0;
		int number = 0;

		while (null != line) {
			line = util.readLine();
			List<String> list = CSVFileUtil.fromCSVLinetoArray(line);
			System.out.println(line); 
			Hospital hospital = new Hospital();
			hospital.setId(parseInt(list.get(0), 0));//
			hospital.setPartnerId(parseLong(list.get(1), -1));//
			hospital.setHospitalId(parseLong(list.get(2), -1)); // 医院ID
			hospital.setCnName(list.get(3));
			hospital.setEnName(list.get(4));//
			hospital.setCnAddress(list.get(5));
			hospital.setEnAddress(list.get(6)); //
			hospital.setCountryCode(parseInt(list.get(7), 0)); // 国家编码
			hospital.setProvinceCode(parseInt(list.get(8), 0));// 省份编码
			hospital.setCityCode(parseInt(list.get(9), 0));// 市编码
			hospital.setRegionCode(list.get(10));// 地区编码 【组合形式 国家,省份,市】
			hospital.setRegionName(list.get(11));// 地区名 【组合形式 国家,省份,市】

			//提取城市
			String city = "";
			if (hospital.getRegionName() != null) {
				String region = hospital.getRegionName();
				String regions[] = region.split(",");
				if (regions != null && regions.length >= 3) {
					city = regions[2];
				} else {
					if( regions.length == 2){
						city = regions[1];
					}
					System.out.println(
							"==============>>>> hospital.getHospitalId" + hospital.getHospitalId() + " city：" + region);
				}

			}

		
			step++;

			// 分段
			if (step < 13450) {
				continue;
			}

			String coordinates[] = getCoordinates(hospital.getCnName(), city);
			int confidence = 0;// 精确度
			
			if (null == coordinates) {//如果医院名字获取坐标失败，通过地址再试一次
				coordinates = getCoordinates(hospital.getCnAddress(), city);
			}
			if (null == coordinates) {// 通过地址或医院名字获取经纬度失败，设置错误码
				setErrorCode(hospital);//列为手工处理数据
			}else{
				confidence = parseInt(coordinates[2], 0);
				// 零时数据
				String tmp_lng = coordinates[0];
				String tmp_lat = coordinates[1];
				int tmp_confid = confidence;

				if (confidence < 75) {// 精确度是否大于75
					coordinates = getCoordinates(hospital.getCnAddress(), city);
					if (null == coordinates) {// 通过地址得到的是空数据，
						confidence = 0;
					} else {
						confidence = parseInt(coordinates[2], 0);
					}
				}

				// 通过详细地址查询的精确度高于名字查询的精确度时，才采用后者
				if (confidence > tmp_confid) {
					hospital.setLongitude(coordinates[0]); // 经度
					hospital.setLatitude(coordinates[1]); // 纬度
					hospital.setConfidence(confidence);// 精确度
				} else {
					hospital.setLongitude(tmp_lng); // 经度
					hospital.setLatitude(tmp_lat); // 纬度
					hospital.setConfidence(tmp_confid);// 精确度
				}
			}
			
			//防封杀
			if (step % 100 == 0) {
				Thread.sleep(1000 * 60);
			} else {
				Thread.sleep(300);
			}
			
			if (null == coordinates || confidence <= 0) {
				System.out.println(list.get(0));
				//continue;
			}

			if (save(hospital)) {
				number++;
			}
		}
		System.out.println("step <===> " + step + " number <===> " + number);
	}

	/**
	 * 错误数据
	 * 
	 * @param hospital
	 */
	private static void setErrorCode(Hospital hospital) {
		hospital.setLongitude("-1"); // 经度
		hospital.setLatitude("-1"); // 纬度
		hospital.setConfidence(-1);// 精确度
	}

	private static int parseInt(String str, int defv) {
		int val = defv;
		if (str != null) {
			try {
				val = Integer.parseInt(str);
			} catch (Exception e) {
				val = defv;
			}
		}
		return val;
	}

	private static long parseLong(String str, long defv) {
		long val = defv;
		if (str != null) {
			try {
				val = Long.parseLong(str);
			} catch (Exception e) {
				val = defv;
			}
		}
		return val;
	}

	/**
	 * 数据库操作
	 * 
	 * @param hospital
	 * @return
	 */
	public static boolean save(Hospital hospital) {

		SqlSession session = sessionFactory.openSession();
		String statement = "com.pa.jk.map.dao.HospitalDao.save";// 映射sql的标识字符串
		// 执行查询返回一个唯一user对象的sql
		int res = session.insert(statement, hospital);
		session.commit();

		return (res > 0 ? true : false);

	}

	/**
	 * 根据关键字 获取地址信息
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String[] getCoordinates(String str, String city) throws IOException {

		BaiDuMapTest baiDuMapTest = new BaiDuMapTest();
		String json = baiDuMapTest.getCoordinate(str, city);
		if("".equals(json)||null == json){
			return null;
		}
		json = json.replaceAll(" ", "");
		Gson gson = new Gson();
		MapTemp mt = null;

		try {
			mt = gson.fromJson(json, MapTemp.class);
		} catch (com.google.gson.JsonSyntaxException e) {
			return null;
		}

		if (null == mt || "1".equals(mt.getStatus())|| "2".equals(mt.getStatus())) {
			System.out.println(json);
			return null;
		}

		String lng = mt.getResult().getLocation().getLng();
		String lat = mt.getResult().getLocation().getLat();
		String confidence = mt.getResult().getConfidence();

		if (mt == null || mt.getResult() == null || mt.getResult().getLocation() == null) {
			return null;
		}

		return new String[] { lng, lat, confidence };
	}

}
