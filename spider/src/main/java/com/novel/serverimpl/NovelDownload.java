package com.novel.serverimpl;

import com.novel.factory.SpiderFactory;
import com.novel.model.NovelContent;
import com.novel.server.ContentSpider;
import com.novel.server.DownLoad;

import java.io.*;
import java.util.UUID;

public class NovelDownload implements DownLoad {
    @Override
    public String downloadChapter(String url, String path) {
        SpiderFactory sf = new SpiderFactory();
        ContentSpider spider = (ContentSpider) sf.getContentSpider(url);
        NovelContent novelContent = spider.getContent(url);
        StringBuilder sb = new StringBuilder();
        sb.append(novelContent.getTitle() + "\r\n").append(novelContent.getContent().replaceAll("<p>", "").replaceAll("</p>", "\r\n"));
        String fileadree = path + "/" + UUID.randomUUID() + ".txt";
        File savfile = new File(fileadree);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(savfile));
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileadree;
    }
}
