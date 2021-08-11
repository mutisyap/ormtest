package io.pmutisya.orm.domain.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

public class JDBCConfiguration {
    private static HikariDataSource hikariDataSource;

    public static HikariDataSource getDataSource(String jdbcUrl, String username, String password) {

        if (hikariDataSource != null) {
            return hikariDataSource;
        }

        HikariConfig config = getHikariConfig(jdbcUrl, username, password);

        hikariDataSource = new HikariDataSource(config);

        return hikariDataSource;
    }

    public static HikariDataSource getDataSource() throws Exception {
        if (hikariDataSource == null) {
            throw new Exception("Hikari datasource not initialized");
        }
        return hikariDataSource;
    }

    private static HikariConfig getHikariConfig(String jdbcUrl, String username, String password) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setReadOnly(false);
        config.setMaxLifetime(60000);
        // just create a pool of one
        config.setMaximumPoolSize(30);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return config;
    }
}
