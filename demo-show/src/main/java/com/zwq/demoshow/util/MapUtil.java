package com.zwq.demoshow.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * created by zwq on 2018/5/11
 */

public class MapUtil {


    @SuppressWarnings("unchecked")
    public static Map<Integer, Integer> getMap(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Map<Integer, Integer>) session.getAttribute("map");
    }
}
