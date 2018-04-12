package com.novel.spider.serverimpl;


import com.novel.spider.model.Chapter;
import com.novel.spider.model.NovelContent;

import com.novel.spider.server.SpiderServer;
import com.novel.spider.serverimpl.spiderinterface.ChapterSpider;
import com.novel.spider.serverimpl.spiderinterface.ContentSpider;
import com.novel.spider.serverimpl.spiderinterface.DownLoad;
import com.novel.util.AbstractSpider;
import org.junit.Test;

import java.util.List;


public class SpiderTest {

    @Test
    public void getHtml() {
        System.out.println(AbstractSpider.getHtml("http://book.qidian.com/info/1009704712#Catalog", "UTF-8"));

    }

    @Test
    public void getChapter() {
        String url = "http://book.qidian.com/info/1009704712#Catalog";
//        String url = "http://book.zhulang.com/491745/";
        SpiderServer spiderServer = new SpiderServer();
        List<List<Chapter>> parts = spiderServer.getChapterByPart(url, false);
        int i = 1;
        for (List<Chapter> p : parts
                ) {
            System.out.println("第" + i + "卷");
            i++;
            for (Chapter c : p
                    ) {
                System.out.println(c);
            }
        }
    }

    @Test
    public void getContent() {
//        String url="http://book.zhulang.com/461915/231166.html";
        String url = "https://read.qidian.com/chapter/Gega9H_HNWqXfJNNZ-YUzw2/teVuK9rXReZMs5iq0oQwLQ2";
        SpiderServer spiderServer = new SpiderServer();

        NovelContent content = spiderServer.getContent(url);
        System.out.println(content.getContent());
        System.out.println(content.getPre());
        System.out.println(content.getIndex());
        System.out.println(content.getNext());
    }

    @Test
    public void downloadOne() {
        String url = "http://book.zhulang.com/461915/231166.html";
        String path = "C:\\Users\\Administrator\\Desktop";
        DownLoad downLoad = new NovelDownload();
        downLoad.downloadChapter(url, path);

    }

    @Test
    public void downloadNovel_free_part() {
        String url = "http://book.zhulang.com/461915/";
        String path = "C:\\Users\\Administrator\\Desktop\\testNovel";
        DownLoad downLoad = new NovelDownload();
        downLoad.downloadNovel(url, false, path);
    }

    @Test
    public void ExecutorServiceDownload() {
        String url = "http://book.zhulang.com/307145/";
//        String url = "https://book.qidian.com/info/1005986994#Catalog";
        NovelDownload downLoad = new NovelDownload();
        List<String> novel = downLoad.downloadNovelByExecutorService(url, false);

        for (int i = 0; i < novel.size(); i++) {
            System.out.println("novel part" + (i + 1) + "  begin");
            downLoad.saveTo(novel.get(i), "C:\\Users\\Administrator\\Desktop\\testNovel" + "\\novel_part" + (i + 1) + ".txt");
//            System.out.println(novel.get(i));
            System.out.println("novel part" + (i + 1) + "  over");
        }
    }
}