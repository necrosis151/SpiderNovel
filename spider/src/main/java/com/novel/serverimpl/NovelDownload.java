package com.novel.serverimpl;

import com.novel.factory.SpiderFactory;
import com.novel.model.Chapter;
import com.novel.model.NovelContent;
import com.novel.server.ChapterSpider;
import com.novel.server.ContentSpider;
import com.novel.server.DownLoad;

import java.io.*;
import java.util.List;

public class NovelDownload implements DownLoad {
    public void saveTo(String content, String path) {
        File file = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
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
    }

    @Override
    public String downloadChapter(String url, String path) {
        ContentSpider spider = (ContentSpider) SpiderFactory.getContentSpider(url);
        NovelContent novelContent = spider.getContent(url);
        StringBuilder sb = new StringBuilder();
        sb.append(novelContent.getTitle() + "\r\n").append(novelContent.getContent().replaceAll("<p>", "").replaceAll("</p>", "\r\n"));
        String file = path + "/" + novelContent.getTitle() + ".txt";

        saveTo(sb.toString(), file);
        return file;
    }

    @Override
    public String downloadNovel(String url, boolean vip, String path) {
        ChapterSpider chapterSpider = (ChapterSpider) SpiderFactory.getChapterSpider(url);
        ContentSpider contentSpider = (ContentSpider) SpiderFactory.getContentSpider(url);
        List<List<Chapter>> parts = chapterSpider.getChapterByPart(url, vip);
        NovelContent novelContent = null;
        StringBuilder sb = null;
        String file = null;
        File save = new File(path);
        if (!save.exists()) {
            save.mkdirs();
        }
        for (int i = 1; i <= parts.size(); i++) {
            List<Chapter> part = parts.get(i - 1);
            sb = new StringBuilder();
            for (Chapter c : part
                    ) {
                novelContent = contentSpider.getContent(c.getUrl());
                sb.append(novelContent.getTitle() + "\r\n").append(novelContent.getContent().replaceAll("<p>", "")
                        .replaceAll("</p>", "\r\n")).append("\r\n").append("\r\n");
                file = path + "/" + novelContent.getTitle() + ".txt";
                saveTo(sb.toString(), file);
            }
        }

        return null;
    }


}
