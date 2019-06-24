package com.github.tone.dao;

import java.util.Collection;
import java.util.Map;

/**
 * @author zhaoxiang.liu
 * @date 2019/6/20
 */
public class MyOgnl {

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    private static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (obj.getClass().isArray()) {
            return ((Object[]) obj).length == 0;
        } else {
            return false;
        }
    }
}
