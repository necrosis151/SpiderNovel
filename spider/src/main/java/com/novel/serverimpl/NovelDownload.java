package com.novel.serverimpl;

import com.novel.factory.SpiderFactory;
import com.novel.model.Chapter;
import com.novel.model.NovelContent;
import com.novel.server.ChapterSpider;
import com.novel.server.ContentSpider;
import com.novel.server.DownLoad;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class NovelDownload implements DownLoad {
    private ChapterSpider chapterSpider=null;
    private ContentSpider contentSpider=null;
    private void saveTo(String content, String path) {
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
        chapterSpider = (ChapterSpider) SpiderFactory.getChapterSpider(url);
        contentSpider = (ContentSpider) SpiderFactory.getContentSpider(url);
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

        return path;
    }

    public String downloadNovelByExecutorService(String url, boolean vip) {
        chapterSpider = (ChapterSpider) SpiderFactory.getChapterSpider(url);
        contentSpider = (ContentSpider) SpiderFactory.getContentSpider(url);
        List<List<Chapter>> parts = chapterSpider.getChapterByPart(url, vip);
        List<String> novel=new ArrayList<>();
        HashMap<String, String> contentlist = new HashMap<>();
//        NovelContent novelContent = null;
//        StringBuilder sb = null;
//        String file = null;
//        File save = new File(path);
//        if (!save.exists()) {
//            save.mkdirs();
//        }
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 1; i <= parts.size(); i++) {
            List<Chapter> part = parts.get(i - 1);
            for (Chapter c : part
                    ) {
                executorService.submit(new FutureTask<String>(new Callable<String>() {
                    @Override
                    public String call() {
                        NovelContent novelContent = contentSpider.getContent(c.getUrl());
                        StringBuilder sb = new StringBuilder();
                        String title = novelContent.getTitle();
                        sb.append(novelContent.getTitle() + "\r\n").append(novelContent.getContent().replaceAll("<p>", "")
                                .replaceAll("</p>", "\r\n")).append("\r\n").append("\r\n");
                        contentlist.put(novelContent.getTitle(), sb.toString());
                        System.out.println(sb);
                        return title + "over";
                    }
                }));
            }
        }
        return "novel over";
//        private String getContentByExecutorService
//        (List < List < Chapter >> parts, HashMap < String, String > contentlist){
//            ExecutorService executorService = Executors.newFixedThreadPool(4);
//
//        }

//        private HashMap<String, String> setContentParts (List < List < Chapter >> parts) {
//            HashMap<String, String> contentlist = new HashMap<>();
//            for (List<Chapter> p :
//                    parts) {
//                for (Chapter c : p
//                        ) {
//                    contentlist.put(c.getTitle(), "");
//                }
//            }
//            return contentlist;
//        }
    }
}
