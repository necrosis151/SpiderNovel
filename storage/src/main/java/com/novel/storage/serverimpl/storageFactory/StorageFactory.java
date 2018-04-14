package com.novel.storage.serverimpl.storageFactory;

import com.novel.storage.serverimpl.storageInterface.StorageSpider;
import com.novel.util.SpiderRuleReader;

import java.util.Map;

public class StorageFactory {
    public  static StorageSpider getStorageSpider(String url){
        Map<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        try {
            return (StorageSpider) Class.forName("com.novel.storage.serverimpl." + spiderContext.get("spider_factory") + "StorageSpider").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

}
