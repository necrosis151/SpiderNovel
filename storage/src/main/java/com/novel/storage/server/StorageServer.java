package com.novel.storage.server;

import com.novel.util.SpiderRuleReader;

import java.util.HashMap;

public class StorageServer {
    private static HashMap<String, String> SpiderContext=null;
    public static Object getIndexSpider(String url) {
        SpiderContext = SpiderRuleReader.getSpiderContext(url);
        if (SpiderContext == null) {
            throw new RuntimeException("不支持的网页");
        }
        String spider = "com.novel.storage.serverimpl."+SpiderContext.get("Index_Spider");
        try {
            if (spider == null || spider.equalsIgnoreCase("default") || spider.equals("")) {
                return Class.forName("com.novel.serverimpl.DefaultIndexSpider").newInstance();
            } else {
                return Class.forName(spider).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
