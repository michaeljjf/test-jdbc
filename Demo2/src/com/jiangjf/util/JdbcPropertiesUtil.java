package com.jiangjf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jdbc.properties读取类
 */
public class JdbcPropertiesUtil {
    static Properties properties = new Properties();

    static {
        System.out.println("读取jdbc.properties配置");
        // 读取配置
        InputStream inputStream = JdbcPropertiesUtil.class.getResourceAsStream("/jdbc.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取某个键的值
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
