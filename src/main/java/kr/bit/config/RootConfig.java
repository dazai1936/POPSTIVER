package kr.bit.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@ComponentScan(basePackages = "kr.bit.controller")
@PropertySource({"classpath:db.properties"})
public class RootConfig {
    @Autowired
    private Environment env;
    @Bean
    public DataSource myDataSource() {
        HikariConfig hikari = new HikariConfig();  // connection pool
        hikari.setDriverClassName(env.getProperty("jdbc.driver"));
        hikari.setJdbcUrl(env.getProperty("jdbc.url"));
        hikari.setUsername(env.getProperty("jdbc.user"));
        hikari.setPassword(env.getProperty("jdbc.password"));

        HikariDataSource myDataSource = new HikariDataSource(hikari);
        return myDataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(myDataSource());
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sessionFactory());
    }
}
