package com.spiderNovel.webapp.server;

public interface NovelIndexServer {
    public void saveNovelIndex(String url);
    public String getNextPage(String url);
}
