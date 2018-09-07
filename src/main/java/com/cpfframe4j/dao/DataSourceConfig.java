package com.cpfframe4j.dao;

import com.cpfframe4j.util.io.PropsUtil;

import java.util.Properties;

public class DataSourceConfig {

    /**
     * 框架 DataSourceConfig 默认配置
     */
    private static final String FFILENAME = "jdbc.properties";
    private static final String FDRIVER = "driver";
    private static final String FURL_KEY = "furl";
    private static final String FUSER_KEY = "fuser";
    private static final String FPASSWORD_KEY = "fpassword";

    private static DataSourceConfig frameDsc = getFrameDsc();

    private String driver = null;
    private String URL = null;
    private String USER = null;
    private String PASSWORD = null;

    private DataSourceConfig(String driver, String url, String user, String password) {
        this.driver = driver;
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    public static DataSourceConfig getInstance(String driver, String url, String user, String password) {
        return new DataSourceConfig(driver, url, user, password);
    }

    public static DataSourceConfig getDsc(String filename, String driverKey, String urlKey, String userKey, String pwdKey) {
        Properties properties = PropsUtil.loadProps(filename);
        String fDriver = properties.getProperty(driverKey);
        String fUrl = properties.getProperty(urlKey);
        String fUser = properties.getProperty(userKey);
        String fPassword = properties.getProperty(pwdKey);
        return new DataSourceConfig(fDriver, fUrl, fUser, fPassword);
    }

    /**
     * 获取框架DataSourceConfig
     */
    public static DataSourceConfig getFrameDsc() {
        if (frameDsc == null) {
            frameDsc = getDsc(FFILENAME, FDRIVER, FURL_KEY, FUSER_KEY, FPASSWORD_KEY);
        }
        return frameDsc;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return URL;
    }

    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }
}
