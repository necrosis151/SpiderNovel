package com.novel.spider.server;

import com.novel.spider.model.Chapter;
import com.novel.spider.model.NovelContent;
import com.novel.spider.serverimpl.spiderinterface.ChapterSpider;
import com.novel.spider.serverimpl.spiderinterface.ContentSpider;
import com.novel.util.SpiderRuleReader;

import java.util.HashMap;
import java.util.List;

public class SpiderServer {
    private HashMap<String, String> SpiderContext = null;

    private Object getChapterSpider(String url) {
        HashMap<String, String> SpiderContext = SpiderRuleReader.getSpiderContext(url);
        if (SpiderContext == null) {
            throw new RuntimeException("不支持的网页");
        }
        String spider = "com.novel.spider.serverimpl." + SpiderContext.get("ChapterSpider");
//        System.out.println(spider);
        try {
            if (spider == null || spider.equalsIgnoreCase("default") || spider.equals("")) {
                return Class.forName("com.novel.serverimpl.DefaultChapterSpider").newInstance();
            } else {
                return Class.forName(spider).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<List<Chapter>> getChapterByPart(String url, boolean vip) {
        ChapterSpider spider = (ChapterSpider) this.getChapterSpider(url);
        return spider.getChapterByPart(url, vip);
    }

    public NovelContent getContent(String url) {
        ContentSpider spider = (ContentSpider) this.getContentSpider(url);
        return spider.getContent(url);
    }

    private Object getContentSpider(String url) {
        SpiderContext = SpiderRuleReader.getSpiderContext(url);
        if (SpiderContext == null) {
            throw new RuntimeException("不支持的网页");
        }
        String spider = "com.novel.spider.serverimpl." + SpiderContext.get("ContentSpider");
        try {
            if (spider == null || spider.equalsIgnoreCase("default") || spider.equals("")) {
                return Class.forName("com.novel.serverimpl.DefaultContentSpider").newInstance();
            } else {
                return Class.forName(spider).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        SpiderServer sf=new SpiderServer();
//       boolean a= sf.getChapterSpider("qidian.com") instanceof IChapter;
//        System.out.println(a);
//    }

}



