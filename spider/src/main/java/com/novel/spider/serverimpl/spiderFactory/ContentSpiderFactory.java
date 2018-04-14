package com.novel.spider.serverimpl.spiderFactory;

import com.novel.spider.serverimpl.spiderinterface.ContentSpider;
import com.novel.util.SpiderRuleReader;

import java.util.Map;

public class ContentSpiderFactory {
    public static ContentSpider getContentSpider(String url) {
        Map<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        ContentSpider spider = null;
        try {
            spider = (ContentSpider) Class.forName("com.novel.spider.serverimpl." + spiderContext.get("spider_factory") + "ContentSpider").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return spider;
    }
}
