package com.novel.storage.serverimpl;

import com.novel.storage.model.Novel_Info;
import com.novel.storage.server.StorageServer;
import com.novel.storage.serverimpl.storageInterface.StorageSpider;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StorageSpiderTest {

    @Test
    public void getInfo() {
        int i=1;
        String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page=43898";
        StorageSpider storageSpider = (StorageSpider) StorageServer.getIndexSpider(url);
        do {
            System.out.println("扫描第"+i+"页");
            i++;
            System.out.println(url);
            List<Novel_Info> novel_infos = storageSpider.getInfo(url);
            url = storageSpider.getNextPage(url);
        } while (url != "");

    }
}