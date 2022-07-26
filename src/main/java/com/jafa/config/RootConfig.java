package com.jafa.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan("com.jafa.mapper")
@EnableScheduling
@ImportResource(value = {
		"classpath:config/security-context.xml"
})
public class RootConfig {

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:mysql://localhost/project");
		config.setUsername("root");
		config.setPassword("1234");
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		return sessionFactoryBean.getObject();
	}
}
