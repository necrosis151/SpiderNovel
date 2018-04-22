package com.spiderNovel.webapp.server;

import com.novel.storage.model.Novel_Info;

import java.util.List;

public interface NovelIndexServer {
    void saveNovelIndex(String url);

    String getNextPage(String url);

    List<Novel_Info> getNovelList(String site, int pageNum, int pageSize);

    List<Novel_Info> getNovelListByConten(String site, int pageNum, int pageSize, String content);

    List<String> getTypeList(String site);

    List<Novel_Info> getNovelByType(String site,String type);
}
