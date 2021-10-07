package com.vt.demo16.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;


@Configuration
@MapperScan(basePackages = "com.vt.demo16.dao.db1", sqlSessionFactoryRef = "sqlSessionFactory", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfigDb1 {

	@Autowired
	@Bean(name = "db1Source")
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	DataSource db1() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "db1TransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("db1Source") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	SqlSessionFactory sqlSessionFactoryDb1() {
		SqlSessionFactory sessionFactory = null;
		try {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(this.db1());
			// bean.setTransactionFactory(new ManagedTransactionFactory());
			sessionFactory = bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	@Bean
	SqlSessionTemplate sqlSessionTemplateDb1() {
		return new SqlSessionTemplate(sqlSessionFactoryDb1());
	}

}
