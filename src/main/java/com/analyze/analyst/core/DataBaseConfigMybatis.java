package com.analyze.analyst.core;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DataBaseConfigMybatis {
    
    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource batisDataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName("org.postgresql.Driver");
        dataSourceConfig.setJdbcUrl(url);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setMaximumPoolSize(10);
        dataSourceConfig.setMinimumIdle(5);
	    dataSourceConfig.setMaxLifetime(1200000);
	    dataSourceConfig.setConnectionTimeout(20000);
	    dataSourceConfig.setIdleTimeout(300000);

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean(name= "batisSqlSessionFactory")
    // Qualifer 는 Bean에 추가 구분자를 붙여주는 방법으로 생성자에서 해당 구분자를 명시하면 그 구분자를 가진 Bean을 주입해준다. 헷갈리기 쉬운 점은 Qualifier가 Bean 이름을 변경한다고 생각할 수 있는데,
    // Bean 이름은 그대로 있고 추가적으로 구분자가 생기는 것이다.
	public SqlSessionFactory batisSqlSessionFactory(@Qualifier("batisDataSource") DataSource batisDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
		sqlSession.setDataSource(batisDataSource);
		sqlSession.setConfigLocation( applicationContext.getResource("classpath:mybatis-config.xml")); // mybatis-config.xml의 경로
		sqlSession.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*SQL.xml")); // 쿼리문을 관리하는 mapper파일의 경로	
        
		return sqlSession.getObject();
		
	}

    @Bean(name = "batisSqlSessionTemplate")
    public SqlSessionTemplate batisSqlSessionTemplate(SqlSessionFactory batisSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(batisSqlSessionFactory);
    }	

}
