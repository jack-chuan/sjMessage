package cn.com.sparknet.sjMessage.datalist.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = TZDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "tzSqlSessionFactory")
public class TZDataSourceConfig {
    static final String PACKAGE = "cn.com.sparknet.sjMessage.datalist.mapper.tz";
    static final String MAPPER_LOCATION = "classpath:mapping/datalist/tz/*Mapper.xml";

    @Value("${spring.datasource.druid.oa12_tz.url}")
    private String url;
    @Value("${spring.datasource.druid.oa12_tz.username}")
    private String username;
    @Value("${spring.datasource.druid.oa12_tz.password}")
    private String password;
    @Value("${spring.datasource.druid.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.druid.max-active}")
    private Integer maxActive;
    @Value("${spring.datasource.druid.min-idle}")
    private Integer minIdle;
    @Value("${spring.datasource.druid.initial-size}")
    private Integer initialSize;
    @Value("${spring.datasource.druid.max-wait}")
    private Long maxWait;
    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.test-while-idle}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.druid.test-on-borrow}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.druid.test-on-return}")
    private Boolean testOnReturn;

    @Bean(name = "tzDataSource")
    public DataSource tzDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        //连接池配置
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setValidationQuery("SELECT 'x' FROM DUAL ");

        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        return dataSource;
    }

    @Bean(name = "tzTransactionManager")
    public DataSourceTransactionManager tzTransactionManager() {
        return new DataSourceTransactionManager(tzDataSource());
    }

    @Bean(name = "tzSqlSessionFactory")
    public SqlSessionFactory tzSqlSessionFactory(@Qualifier("tzDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("cn.com.sparknet.sjMessage.datalist.entity.tz");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(TZDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
