package com.novel.storage.server;

import com.novel.storage.model.Novel_Info;
import com.novel.storage.serverimpl.storageFactory.StorageFactory;
import com.novel.storage.serverimpl.storageInterface.StorageSpider;

import java.util.List;

public class StorageServer {

    public static List<Novel_Info> getNovelInfos(String url) {
        StorageSpider spider=StorageFactory.getStorageSpider(url);
        return  spider.getInfos(url);
    }
    public static String getNextPage(String url){
        StorageSpider spider=StorageFactory.getStorageSpider(url);
        return spider.getNextPage(url);
    }

}
