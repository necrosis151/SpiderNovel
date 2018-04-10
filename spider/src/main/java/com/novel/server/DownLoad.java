package com.novel.server;

import com.novel.model.Chapter;

import java.util.List;

public interface DownLoad {
    public String downloadChapter(String url,String savadree);
    public String downloadNovel(String url, boolean vip,String path);

}
