package com.novel.server;

import com.novel.model.Chapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ChapterSpider {
    List<List<Chapter>> getChapterByPart(String url);
}
