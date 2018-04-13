package com.novel.storage.serverimpl;

import com.novel.storage.model.Novel_Info;
import com.novel.storage.server.StorageServer;
import com.novel.storage.serverimpl.storageInterface.StorageSpider;
import org.junit.Test;

import java.util.List;

public class StorageSpiderTest {

    @Test
    public void getInfo() {
        int i = 1;
        String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page=43905";
        StorageSpider storageSpider = (StorageSpider) StorageServer.getIndexSpider(url);
        while (!url.equals("http:javascript:;")) {
            System.out.println("扫描第" + i + "页");
            i++;

            List<Novel_Info> novel_infos = storageSpider.getInfo(url);
            url = storageSpider.getNextPage(url);
            for (Novel_Info n:novel_infos
                 ) {
                System.out.println(n);
            }

        }

    }
}