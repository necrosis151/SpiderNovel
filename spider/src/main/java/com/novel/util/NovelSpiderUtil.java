package com.novel.util;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovelSpiderUtil {
    private volatile static HashMap<String, HashMap<String, String>> CONTEXT_MAP = new HashMap<>();


    private NovelSpiderUtil() {
    }

    public static Map<String,HashMap<String,String>> getContextMap() {
        if (CONTEXT_MAP.size() == 0) {
            synchronized (NovelSpiderUtil.class) {
                if (CONTEXT_MAP.size() == 0) {
                    ini();
                }
            }
        }
        return CONTEXT_MAP;
    }
    public static  HashMap<String,String> getSpiderContext(String url){
        for (String key:getContextMap().keySet()
             ) {
            if (url.contains(key)){
                return getContextMap().get(key);
            }
        }
        return null;
    }

    public static void reloadContext() {
        CONTEXT_MAP.clear();
    }

    private static void ini() {

        SAXReader reader = new SAXReader();
        Document doc = null;
        InputStream in = NovelSpiderUtil.class.getClassLoader().getResourceAsStream("Spider_Rule.xml");
        try {
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> sites = root.elements("site");

            for (Element site : sites
                    ) {
                List<Element> subs = site.elements();
                HashMap<String, String> subMap = new HashMap<String, String>();
                for (Element sub : subs) {
                    String name = sub.getName();
                    String text = sub.getTextTrim();
//                    System.out.println(name + "   " + text);
                    subMap.put(name, text);
                }
                CONTEXT_MAP.put(subMap.get("url"), subMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        ini();
//
//        System.out.println(CONTEXT_MAP.size());
//        reloadContext();
//        System.out.println(CONTEXT_MAP.size());
//        CONTEXT_MAP = getContextMap();
//        System.out.println(CONTEXT_MAP.size());
//    }
}
