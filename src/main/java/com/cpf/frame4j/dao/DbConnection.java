package com.cpf.frame4j.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnection.class);

    /**
     * 存放数据库连接
     */
    private static final ThreadLocal<Connection> CONN_HOLDER;

    private static final BasicDataSource DATA_SOURCE;

    static {
        CONN_HOLDER = new ThreadLocal<>();

        DataSourceConfig frameDsc = DataSourceConfig.getFrameDsc();

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(frameDsc.getDriver());
        DATA_SOURCE.setUrl(frameDsc.getUrl());
        DATA_SOURCE.setUsername(frameDsc.getUser());
        DATA_SOURCE.setPassword(frameDsc.getPassword());

    }

    /**
     * 获取框架数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = CONN_HOLDER.get();
        if (conn == null) {
            try {
//                DataSourceConfig frameDsc = DataSourceConfig.getFrameDsc();
//                conn = DriverManager.getConnection(frameDsc.getUrl(), frameDsc.getUser(), frameDsc.getPassword());
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("connect error", e);
                throw new RuntimeException(e);
            } finally {
                CONN_HOLDER.set(conn);
            }
        }
        return conn;
    }

    /**
     * dbcp2 数据源管理不需要关闭数据源
     * 关闭框架数据库连接
     */
//    public static void closeConnection() {
//        Connection conn = CONN_HOLDER.get();
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                LOGGER.error("close connection error", e);
//                throw new RuntimeException(e);
//            } finally {
//                CONN_HOLDER.remove();
//            }
//        }
//    }

}
