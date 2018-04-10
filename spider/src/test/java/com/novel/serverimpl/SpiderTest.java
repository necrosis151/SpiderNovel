package com.novel.serverimpl;

import com.novel.factory.SpiderFactory;
import com.novel.model.Chapter;
import com.novel.model.NovelContent;
import com.novel.server.ChapterSpider;
import com.novel.server.ContentSpider;
import com.novel.server.DownLoad;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class SpiderTest {

    @Test
    public void getHtml() {
        AbstractSpider spider = new AbstractSpider();
        System.out.println(spider.getHtml("http://book.qidian.com/info/1009704712#Catalog", "UTF-8"));

    }

    @Test
    public void getChapter() {
         String url = "http://book.qidian.com/info/1009704712#Catalog";
//        String url = "http://book.zhulang.com/491745/";
        ChapterSpider spider = (ChapterSpider) new SpiderFactory().getChapterSpider(url);
        List<List<Chapter>> parts = spider.getChapterByPart(url, false);
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
        ContentSpider spider = (ContentSpider) new SpiderFactory().getContentSpider(url);
        NovelContent content = spider.getContent(url);
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
            System.out.println("novel part" + (i+1) + "  begin");
            downLoad.saveTo(novel.get(i),"C:\\Users\\Administrator\\Desktop\\testNovel"+"\\novel_part" + (i+1)+".txt");
//            System.out.println(novel.get(i));
            System.out.println("novel part" + (i+1) + "  over");
        }
    }
}