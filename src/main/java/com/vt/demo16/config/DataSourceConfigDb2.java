package com.vt.demo16.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages = "com.vt.demo16.dao.db2", sqlSessionFactoryRef = "sqlSessionFactory", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfigDb2 {
	
	@Autowired
	@Bean(name = "db2Source")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	DataSource db2() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "db2TransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("db2Source") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	SqlSessionFactory sqlSessionFactoryDb2() {
		SqlSessionFactory sessionFactory = null;
		try {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(this.db2());
			// bean.setTransactionFactory(new ManagedTransactionFactory());
			sessionFactory = bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	@Bean
	SqlSessionTemplate sqlSessionTemplateDb2() {
		return new SqlSessionTemplate(sqlSessionFactoryDb2());
	}

}
