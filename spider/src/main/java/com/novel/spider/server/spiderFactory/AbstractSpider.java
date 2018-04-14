package com.novel.spider.server.spiderFactory;

import com.novel.spider.model.Chapter;
import com.novel.spider.model.NovelContent;
import com.novel.spider.serverimpl.spiderinterface.ChapterSpider;
import com.novel.spider.serverimpl.spiderinterface.ContentSpider;
import com.novel.util.SpiderRuleReader;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractSpider {

    abstract public List<List<Chapter>> getChapterByPart(String url, boolean vip);

    abstract public NovelContent getContent(String url);

}



