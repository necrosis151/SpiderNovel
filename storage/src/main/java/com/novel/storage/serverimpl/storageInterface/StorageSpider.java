package com.novel.storage.serverimpl.storageInterface;

import com.novel.storage.model.Novel_Info;

import java.util.List;

public interface StorageSpider {
    public List<Novel_Info> getInfos(String url);
    public String getNextPage(String url);
}
