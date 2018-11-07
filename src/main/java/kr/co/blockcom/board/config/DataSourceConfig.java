package kr.co.blockcom.board.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan({"kr.co.blockcom.*"})
public class DataSourceConfig {
	//TODO 설정 프로 퍼티로 빼기
	@Bean(name="dataSource",destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/btrade_board?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("bcc20171101!");
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
