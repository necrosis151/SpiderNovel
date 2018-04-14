package com.novel.spider.serverimpl.spiderFactory;

import com.novel.spider.serverimpl.QidianChapterSpider;
import com.novel.spider.serverimpl.spiderinterface.ChapterSpider;
import com.novel.util.SpiderRuleReader;

import java.util.Map;

public class ChapterSpiderFactory {
    public static ChapterSpider getChapterSpider(String url) {
        Map<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        ChapterSpider spider = null;
        try {
            spider = (ChapterSpider) Class.forName("com.novel.spider.serverimpl." + spiderContext.get("spider_factory") + "ChapterSpider").newInstance();
//            spider = (ChapterSpider) Class.forName(spider.getName()).newInstance();
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

