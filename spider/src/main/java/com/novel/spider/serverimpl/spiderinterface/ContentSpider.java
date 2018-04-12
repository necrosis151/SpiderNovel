package com.novel.spider.serverimpl.spiderinterface;

import com.novel.spider.model.NovelContent;

public interface ContentSpider {
    NovelContent getContent(String url);
}
