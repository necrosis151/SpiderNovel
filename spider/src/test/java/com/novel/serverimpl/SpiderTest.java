package com.novel.serverimpl;

import com.novel.factory.SpiderFactory;
import com.novel.model.Chapter;
import com.novel.model.NovelContent;
import com.novel.server.ChapterSpider;
import com.novel.server.ContentSpider;
import com.novel.util.NovelSpiderUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SpiderTest {

    @Test
    public void getHtml() {
        AbstractSpider spider = new AbstractSpider();
        System.out.println(spider.getHtml("http://book.qidian.com/info/1009704712#Catalog", "UTF-8"));

    }

    @Test
    public void getChapter() {
        // String url = "http://book.qidian.com/info/1009704712#Catalog";
        String url = "http://book.zhulang.com/461915/";
        ChapterSpider spider = (ChapterSpider) new SpiderFactory().getChapterSpider(url);
        List<List<Chapter>> parts = spider.getChapterByPart(url);
        int i = 1;
        for (List<Chapter> p : parts
                ) {
            System.out.println("µÚ" + i + "¾í");
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
    }
}