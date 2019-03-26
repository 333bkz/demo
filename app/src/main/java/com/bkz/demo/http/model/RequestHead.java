package com.bkz.demo.http.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 接口请求头部信息
 * </p>
 * <p>
 * 用于校验接口访问权限
 * </p>
 *
 * @author yangtanghe
 * @since iWork v1.0
 * @version $Id: RequestHead.java 1.0 Dec 29, 2014 $
 */
@SuppressWarnings("serial")
public class RequestHead implements Serializable {
    /** APP_ID(访问权限) */
    public String appID;
    /** 登录用户ID(账户Id) */
    public long loginID;
    /** 登录sessionID */
    public String sessionID;
    /** 语言编码 */
    public String lanCode;
    /** 操作系统类型 1-Android 或 2-IOS */
    public int osVersion;
    /** imei码或IOS的设备token */
    public String imeiNum;
    /** 软件版本 2.0.1*/
    public String softwareVersion;
    /** 配置下发总接口的key值 */
    public String restKey;
    /** H1、G1账号*/
    public int platfromType;

    //头部对象转Map
    public Map convertMap() {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();
            try {
                Object o = field.get(this);
                if (o != null)
                    map.put(varName, o.toString());
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
}