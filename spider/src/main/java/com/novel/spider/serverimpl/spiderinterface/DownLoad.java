package com.novel.spider.serverimpl.spiderinterface;

public interface DownLoad {
    public String downloadChapter(String url,String savadree);
    public String downloadNovel(String url, boolean vip,String path);

}
