package com.novel.server;

import com.novel.model.Chapter;
import com.novel.model.NovelContent;

import java.util.List;

public interface ContentSpider {
    NovelContent getContent(String url);
}
