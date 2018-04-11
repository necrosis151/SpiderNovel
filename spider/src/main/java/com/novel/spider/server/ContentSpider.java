package com.novel.spider.server;

import com.novel.spider.model.NovelContent;

public interface ContentSpider {
    NovelContent getContent(String url);
}
