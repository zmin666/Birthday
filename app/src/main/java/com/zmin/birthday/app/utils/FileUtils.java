package com.zmin.birthday.app.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZhangMin
 * @date: 2017/7/11 16:23
 * @desc:
 */
public class FileUtils {
    public static Map<String, Object> getFileMap(int act) {
        HashMap<String, Object> map = new HashMap<>();
        long time = System.currentTimeMillis();
        map.put("time", time);
        map.put("act", act);
        map.put("md5", MD5Utils.getMd5(time + "" + act));
        map.put("o_uid", act);
        return map;
    }
}
