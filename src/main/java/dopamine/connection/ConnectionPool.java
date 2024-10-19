package dopamine.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import dopamine.constants.Constant;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Constant.CONNECTION_URL);
        config.setUsername(Constant.USER_NAME);
        config.setPassword(Constant.PASSWORD);
        config.setDriverClassName(Constant.DB_DRIVER);
        config.setMinimumIdle(Constant.DB_MIN_CONNECTIONS);
        config.setMaximumPoolSize(5); // Set this to a value <= max_user_connections
        config.setConnectionTimeout(30000); // 30 seconds
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
