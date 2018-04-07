package com.novel.serverimpl;

import com.novel.factory.SpiderFactory;
import com.novel.model.Chapter;
import com.novel.server.ChapterSpider;
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
        String url = "http://book.qidian.com/info/1009704712#Catalog";
        ChapterSpider spider= (ChapterSpider) new SpiderFactory().getChapterSpider(url);
        List<List<Chapter>> parts=spider.getChapterByPart(url);
        int i=1;
        for (List<Chapter> p:parts
             ) {
            System.out.println("µÚ"+i+"¾í");
            i++;
            for (Chapter c:p
                 ) {
                System.out.println(c);
            }
        }
    }
}