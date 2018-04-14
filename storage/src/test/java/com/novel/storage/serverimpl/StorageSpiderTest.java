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
//        String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page=43995";
        String url = "http://www.zhulang.com/Shuku/index/main/all/sub/all/size/0/flag/0/time/0/type/0/order/0/ini/0/p/1186.html";
        List<Novel_Info> novel_infos = StorageServer.getNovelInfos(url);
//        for (Novel_Info n:novel_infos
//             ) {
//            System.out.println(n);
//        }
        String next = StorageServer.getNextPage(url);
        System.out.println(next);
        while (!next.equals("")) {
            System.out.println("扫描第" + i + "页");
            i++;

            novel_infos = StorageServer.getNovelInfos(next);
            for (Novel_Info n : novel_infos
                    ) {
                System.out.println(n);
            }
            next = StorageServer.getNextPage(next);
            System.out.println(next);
        }
    }
}