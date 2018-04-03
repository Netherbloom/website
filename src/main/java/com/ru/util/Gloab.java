package com.ru.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2018/3/28.
 * 读取配置文件
 */
public class Gloab {
    /**
     * 服务实例编号
     */
    public static String ServerInstance;
    /**
     * 项目地址
     */
    public static String WebPath;
    /**
     * 天气接口code
     */
    public static String weather_appcode;
    /**
     * 驾照试题接口code
     */
    public static String jz_appcode;
    /**
     * 文件地址
     */
    public static String FilePath;
    /**
     * 初始账号
     */
    public static String init_account;
    /**
     * 项目名称
     */
    public static final String PROJECT_NAME="website";

    /**
     * 读取配置
     * @throws IOException
     */
    public static boolean load() {

        String strFileName = "/resources/gloab.properties";

        Properties ps = new Properties();
        try {
            // 加载
            ps.load(Gloab.class.getResourceAsStream(strFileName));
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        ServerInstance = ps.getProperty("server_instance");
        if (null == ServerInstance) ServerInstance = "signle"; // 默认单例

        // path
        WebPath = ps.getProperty("web_path");
        FilePath = ps.getProperty("file_path");
        init_account = ps.getProperty("init_account");
        weather_appcode = ps.getProperty("weather_appcode");
        jz_appcode = ps.getProperty("jz_appcode");
        return true;
    }
}
