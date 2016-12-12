//package com.pa.jk.pay.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.mapper.MapperFactoryBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import com.pa.jk.map.bean.Hospital;
//import com.pa.jk.pay.bean.OrderInfo;
//
//@SuppressWarnings("rawtypes")
//@Configuration
//public class DatabaseConfiguration {
//
//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource dataSource() {
//		return new org.apache.tomcat.jdbc.pool.DataSource();
//	}
//
//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//
//		sqlSessionFactoryBean.setDataSource(dataSource());
//		sqlSessionFactoryBean.setTypeAliases(new Class[] { Hospital.class, OrderInfo.class });
//		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
//
//		return sqlSessionFactoryBean.getObject();
//
//	}
//
//	private <T> MapperFactoryBean getMapper(Class<T> mapperInterface) {
//		MapperFactoryBean<T> mapperFactoryBean = new MapperFactoryBean<T>();
//
//		try {
//
//			mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory());
//			mapperFactoryBean.setMapperInterface(mapperInterface);
//
//		} catch (Exception ex) {
//			// logger.error("error when create mapper: ", ex);
//			throw new RuntimeException(ex);
//		}
//		return mapperFactoryBean;
//	}
//
//	@Bean
//	public MapperFactoryBean hospitalMapper() {
//		return getMapper(Hospital.class);
//	}
//
//	@Bean
//	public MapperFactoryBean orderMapper() {
//		return getMapper(OrderInfo.class);
//	}
//
//}
