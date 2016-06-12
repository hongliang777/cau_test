package com.yhl.javaapi;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: hongliang
 * Date: 16-6-12
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class Go {
    public static void main(String[] args) {
        // 打印环境变量
        Properties properties = System.getProperties();
        Iterator it =  properties.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
            System.out.print(entry.getKey()+"=");
            System.out.println(entry.getValue());
        }
    }
}
