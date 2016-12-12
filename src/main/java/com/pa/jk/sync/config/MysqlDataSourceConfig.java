package com.pa.jk.sync.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
@Configuration
@MapperScan(basePackages = "com.pa.jk.sync.config", sqlSessionTemplateRef = "syncSqlSessionTemplate")
public class MysqlDataSourceConfig {

	@Bean(name = "syncDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.sync.salve")
	public DataSource dataSource() {
		System.out.println(">>222222222222222222222222>>>>");
		return (DataSource) DataSourceBuilder.create().build();
	}

	@Bean(name = "syncSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("syncDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/syncSlaveMapper.xml"));
		return bean.getObject();
	}

	@Bean(name = "syncTransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("syncDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "syncSqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("syncSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}