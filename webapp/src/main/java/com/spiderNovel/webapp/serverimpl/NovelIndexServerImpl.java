package com.spiderNovel.webapp.serverimpl;

import com.novel.storage.model.Novel_Info;
import com.novel.storage.server.StorageServer;
import com.novel.util.SpiderRuleReader;
import com.spiderNovel.webapp.server.NovelIndexServer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Component
public class NovelIndexServerImpl implements NovelIndexServer {

    @Override
    public void saveNovelIndex(String url) {
        HashMap<String,String> spiderContext=SpiderRuleReader.getSpiderContext(url);
        List<Novel_Info> infoList=StorageServer.getNovelInfos(url);

    }

    @Override
    public String getNextPage(String url) {
        return null;
    }
}
