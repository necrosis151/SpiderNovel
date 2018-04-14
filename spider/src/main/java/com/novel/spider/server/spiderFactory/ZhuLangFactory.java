package com.novel.spider.server.spiderFactory;

import com.novel.spider.model.Chapter;
import com.novel.spider.model.NovelContent;
import com.novel.spider.serverimpl.ZhuLangChapterSpider;
import com.novel.spider.serverimpl.ZhuLangContentSpider;

import java.util.List;

public class ZhuLangFactory extends AbstractSpider {
    @Override
    public List<List<Chapter>> getChapterByPart(String url, boolean vip) {
        return new ZhuLangChapterSpider().getChapterByPart(url, vip);
    }

    @Override
    public NovelContent getContent(String url) {
        return new ZhuLangContentSpider().getContent(url);
    }
}
