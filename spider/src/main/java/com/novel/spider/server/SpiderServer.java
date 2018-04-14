package com.novel.spider.server;

import com.novel.spider.server.spiderFactory.AbstractSpider;
import com.novel.util.SpiderRuleReader;

import java.util.Map;

public class SpiderServer {
    public static AbstractSpider getSpider(String url) {
        Map<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        try {
            return (AbstractSpider) Class.forName("com.novel.spider.server.spiderFactory."+spiderContext.get("spider_factory")).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
