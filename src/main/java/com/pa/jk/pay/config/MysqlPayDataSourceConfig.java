package com.pa.jk.pay.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;

@Configuration
@MapperScan(basePackages = MysqlPayDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "paySqlSessionTemplate")
public class MysqlPayDataSourceConfig {
	static final String PACKAGE = "com.pa.jk.pay.dao";

	@Autowired
	private Environment env;

	// @ConfigurationProperties(prefix = "spring.datasource.test")
	@Primary
	@Bean(name = "payDataSource")
	public DataSource dataSource() throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("spring.datasource.test.driver-class-name"));
		props.put("url", env.getProperty("spring.datasource.test.url"));
		props.put("username", env.getProperty("spring.datasource.test.username"));
		props.put("password", env.getProperty("spring.datasource.test.password"));
		// DataSource dataSource = new DruidDataSource();
		return DruidDataSourceFactory.createDataSource(props);
		// return (DataSource) DataSourceBuilder.create().build();
	}

	@Bean(name = "paySqlSessionFactory")
	public SqlSessionFactory paySqlSessionFactory(@Qualifier("payDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/orderInfoMapper.xml"));
		return bean.getObject();
	}

	@Bean(name = "payTransactionManager")
	public DataSourceTransactionManager payTransactionManager(@Qualifier("payDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "paySqlSessionTemplate")
	public SqlSessionTemplate SqlSessionTemplate(
			@Qualifier("paySqlSessionFactory") SqlSessionFactory paySqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(paySqlSessionFactory);
	}
}