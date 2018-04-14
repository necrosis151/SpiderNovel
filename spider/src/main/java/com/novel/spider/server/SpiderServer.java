package com.novel.spider.server;

import com.novel.spider.model.Chapter;
import com.novel.spider.model.NovelContent;
import com.novel.spider.serverimpl.QidianChapterSpider;
import com.novel.spider.serverimpl.spiderFactory.ChapterSpiderFactory;
import com.novel.spider.serverimpl.spiderFactory.ContentSpiderFactory;
import com.novel.spider.serverimpl.spiderinterface.ChapterSpider;
import com.novel.spider.serverimpl.spiderinterface.ContentSpider;

import java.util.List;

public class SpiderServer {
    public static List<List<Chapter>> getChapters(String url, boolean vip) {
        ChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);
        return spider.getChapterByPart(url, vip);
    }
    public static NovelContent getContent(String url){
        ContentSpider spider= ContentSpiderFactory.getContentSpider(url);
        return spider.getContent(url);
    }
}
