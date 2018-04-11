package com.novel.spider.server;

import com.novel.spider.model.Chapter;

import java.util.List;

public interface ChapterSpider {
    List<List<Chapter>> getChapterByPart(String url,boolean vip);
}
