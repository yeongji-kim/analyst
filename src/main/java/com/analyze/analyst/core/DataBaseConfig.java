package com.analyze.analyst.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableJpaRepositories (
    basePackages = "com.toyproject.analyst.repos"    // repository관리할 패키지
    , entityManagerFactoryRef = "entityManagerFactory"
    , transactionManagerRef = "transactionManager"
)
public class DataBaseConfig {
    private static final String DEFAULT_NAMING_STRATEGY_STRING = "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl";
    
    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    @Primary    // 해당  bean 을 우선적으로 선택하도록 하는 annotation
    public javax.sql.DataSource defaultDataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName("org.postgresql.Driver");
        dataSourceConfig.setJdbcUrl(url);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setMaximumPoolSize(10);
        dataSourceConfig.setMinimumIdle(5);
        dataSourceConfig.setMaxLifetime(1200000);
	    dataSourceConfig.setConnectionTimeout(20000	);
	    dataSourceConfig.setIdleTimeout(300000);

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entiityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String,String> propertiesHashMap = new HashMap<>();
        propertiesHashMap.put("spring.jpa.hibernate.naming.physical-strategy", DEFAULT_NAMING_STRATEGY_STRING);

        LocalContainerEntityManagerFactoryBean rep =
            builder.dataSource(defaultDataSource())
            .packages("com.toyproject.analyst.domain") 
            .properties(propertiesHashMap)
            .build();

        return rep;
    }

    @Bean(name = "transactionManager")
    @Primary
	PlatformTransactionManager transactionManager(
	    EntityManagerFactoryBuilder builder) {
	  return new JpaTransactionManager(entiityManagerFactory(builder).getObject());
	}
}
