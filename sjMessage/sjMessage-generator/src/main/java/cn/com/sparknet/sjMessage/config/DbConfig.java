/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.com.sparknet.sjMessage.config;

import cn.com.sparknet.sjMessage.mapper.*;
import cn.com.sparknet.sjMessage.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据库配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class DbConfig {
    @Value("${sjMessage.database: oracle}")
    private String database;
    @Autowired
    private MySQLGeneratorMapper mySQLGeneratorMapper;
    @Autowired
    private OracleGeneratorMapper oracleGeneratorMapper;
    @Autowired
    private SQLServerGeneratorMapper sqlServerGeneratorMapper;
    @Autowired
    private PostgreSQLGeneratorMapper postgreSQLGeneratorMapper;

    @Bean
    @Primary
    public GeneratorMapper getGeneratorDao(){
        if("mysql".equalsIgnoreCase(database)){
            return mySQLGeneratorMapper;
        }else if("oracle".equalsIgnoreCase(database)){
            return oracleGeneratorMapper;
        }else if("sqlserver".equalsIgnoreCase(database)){
            return sqlServerGeneratorMapper;
        }else if("postgresql".equalsIgnoreCase(database)){
            return postgreSQLGeneratorMapper;
        }else {
            throw new RRException("不支持当前数据库：" + database);
        }
    }
}
