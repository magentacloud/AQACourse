package org.task4.db.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.task3.env.Env;
import org.task3.env.config.DBConfig;

import javax.sql.DataSource;

@UtilityClass
@Slf4j
public class DataSourceProvider {
    public static DataSource getH2DataSource(DBConfig dbConfig){
        String jdbcUrl = "jdbc:h2:file:" + dbConfig.dbPath();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setPoolName("H2");

        return new HikariDataSource(config);
    }
}
