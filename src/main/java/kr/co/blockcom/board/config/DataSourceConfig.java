package kr.co.blockcom.board.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;



@Configuration
@MapperScan({"kr.co.blockcom.*"})
@PropertySource(value = { "classpath:application.properties" }, name = "datasource")
public class DataSourceConfig {
	
	@Autowired
	private Environment env;
		
	@Bean(name="dataSource",destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("datasource.url"));
		dataSource.setUsername(env.getProperty("datasource.username"));
		dataSource.setPassword(env.getProperty("datasource.password"));
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:kr/co/blockcom/board/biz/**/mapper/*Mapper.xml"));
		return sqlSessionFactory;
	}
	@Bean(name="sqlSessionTemplate")
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){		
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	

}
