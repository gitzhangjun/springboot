package com.test.baidu.map.cache;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pa.jk.map.bean.Hospital;
import com.test.baidu.map.csv.BaiDuMapTest;
/**
 * JVM缓存
 * @author zhangjun
 *
 */
public class HospitalCacheUtil {

	private static final HashMap<Long, Hospital> cache = new HashMap<Long, Hospital>();

	private static SqlSessionFactory sessionFactory = null;

	static {
		// mybatis的配置文件
		String resource = "conf.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = BaiDuMapTest.class.getClassLoader().getResourceAsStream(resource);
		// 构建sqlSession的工厂
		sessionFactory = new SqlSessionFactoryBuilder().build(is);

	}

	public static void main(String[] args) {
		Map<Long, Hospital> map = init();
		System.out.println(map.get(new Long(10579)).getCnAddress());
	}

	/**
	 * 数据库操作
	 * 
	 * @param hospital
	 * @return
	 */
	public static Map<Long, Hospital> init() {

		SqlSession session = sessionFactory.openSession();
		String statement = "com.pa.jk.map.dao.HospitalDao.loadAll";// 映射sql的标识字符串
		// 执行查询返回一个唯一user对象的sql
		List<Hospital> res = session.selectList(statement);
		for (Hospital h : res) {
			cache.put(h.getHospitalId(), h);
		}
		return cache;
	}

	/**
	 * 根据医院ID 单个获取医院信息
	 * @param hospitalId
	 * @return
	 */
	public static Hospital getHospitalById(Long hospitalId){
		
		if(hospitalId.intValue() <= 0){
			return null;
		}
		
		if(cache.isEmpty()){
			init();
		}
		
		return cache.get(hospitalId);
	}
}
