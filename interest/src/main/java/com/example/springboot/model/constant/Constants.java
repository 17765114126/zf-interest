package com.example.springboot.model.constant;

/**
 * 通用常量信息
 * 
 * @author eaf
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌有效期（分钟）
     */
    public final static long TOKEN_EXPIRE = 720;

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 顶级id
     */
    public static final String TOP_ID = "0";

    /**
     * cookie token name
     */
    public static final String ACCESS_TOKEN = "access_token";

    /**
     * cookie path
     */
    public static final String COOKIE_PATH = "/";

    /**
     * 环境prod
     */
    public final static String ENV_PROD = "prod";

    public final static String ENV_DEV = "dev";

    public final static String ENV_LOCAL = "local";

    public final static String ENV_TEST = "test";

    public final static String ENV_PRE = "pre";

    public final static String NEED_RESET_PASSWORD = "898";

    /**
     * cookie有效期（分钟）
     */
    public final static int COOKIE_EXPIRE = 60 * 60 * 24 * 7;

    /**
     * 链接超时时间毫秒
     */
    public static final Integer MAX_CONNECTION_TIMEOUT = 30000;

    /**
     * 链接超时时间毫秒
     */
    public static final Integer MID_CONNECTION_TIMEOUT = 15000;

    /**
     * 链接超时时间毫秒
     */
    public static final Integer MIN_CONNECTION_TIMEOUT = 10000;

}
