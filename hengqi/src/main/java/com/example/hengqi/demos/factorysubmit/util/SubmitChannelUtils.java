package com.example.hengqi.demos.factorysubmit.util;

import java.util.HashMap;
import java.util.Map;

public class SubmitChannelUtils {

    public static Map<String,SubmitChannelAbstract> map = new HashMap<>();

    static {
        map.put("t1", new T1Utile());
        map.put("t2", new T2Utile());
    }

    static {
        map.put("t3", new T3Utile());
    }


}
