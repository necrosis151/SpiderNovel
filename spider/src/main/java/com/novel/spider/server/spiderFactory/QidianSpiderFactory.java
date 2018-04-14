package com.novel.spider.server.spiderFactory;

import com.novel.spider.model.Chapter;
import com.novel.spider.model.NovelContent;
import com.novel.spider.serverimpl.QiDianChapterSpider;
import com.novel.spider.serverimpl.QiDianContentSpider;

import java.util.List;

public class QidianSpiderFactory extends AbstractSpider {

    @Override
    public List<List<Chapter>> getChapterByPart(String url, boolean vip) {
        return new QiDianChapterSpider().getChapterByPart(url, vip);
    }

    @Override
    public NovelContent getContent(String url) {
        return new QiDianContentSpider().getContent(url);
    }
}
