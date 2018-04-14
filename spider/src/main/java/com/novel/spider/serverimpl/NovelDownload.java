package com.novel.spider.serverimpl;

import com.novel.spider.model.Chapter;
import com.novel.spider.model.NovelContent;
import com.novel.spider.server.SpiderServer;
import com.novel.spider.serverimpl.spiderinterface.DownLoad;
import com.novel.util.AbstractSpider;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class NovelDownload implements DownLoad {
    private AbstractSpider spider = null;
    public void saveTo(String content, String path) {
        File file = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            if (content.length() == 0) {
                content = "非会员无法下载";
            }
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
        NovelContent novelContent =SpiderServer.getContent(url);
        StringBuilder sb = new StringBuilder();
        sb.append(novelContent.getTitle() + "\r\n").append(novelContent.getContent().replaceAll("<p>", "").replaceAll("</p>", "\r\n"));
        String file = path + "/" + novelContent.getTitle() + ".txt";

        saveTo(sb.toString(), file);
        return file;
    }

    //未启用连接池
    @Override
    public String downloadNovel(String url, boolean vip, String path) {
        List<List<Chapter>> parts = SpiderServer.getChapters(url, vip);
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
                novelContent = SpiderServer.getContent(url);
                sb.append(novelContent.getTitle() + "\r\n").append(novelContent.getContent().replaceAll("<p>", "")
                        .replaceAll("</p>", "\r\n")).append("\r\n").append("\r\n");
                file = path + "/" + novelContent.getTitle() + ".txt";
                saveTo(sb.toString(), file);
            }
        }
        return path;
    }

    public List<String> downloadNovelByExecutorService(String url, boolean vip) {
        NovelContent novelContent = null;
        List<List<Chapter>> parts = SpiderServer.getChapters(url, vip);
        List<List<String>> contentTitleList = this.getContentTitleList(parts);
        HashMap<String, String> contentMap = new HashMap<>();
        List<String> novel = new ArrayList<>();
        List<FutureTask<String>> futureTaskList = new ArrayList<>();
        FutureTask<String> futureTask = null;
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        StringBuilder sb = new StringBuilder();
        //连接池下载
        for (int i = 1; i <= parts.size(); i++) {
            List<Chapter> part = parts.get(i - 1);
            for (Chapter c : part
                    ) {
                novelContent = SpiderServer.getContent(c.getUrl());
                futureTask = new FutureTask<String>(new DownloadCallable(novelContent, contentMap));
                futureTaskList.add(futureTask);
                executorService.submit(futureTask);
            }
        }
        System.out.println("线程检查");
        //检测线程是否完结
        for (FutureTask<String> f : futureTaskList
                ) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        //赋值
        for (List<String> p : contentTitleList
                ) {
            sb.delete(0, sb.length());
            for (String key : p
                    ) {
                sb.append(contentMap.get(key));
            }
            novel.add(sb.toString());
        }

        return novel;
    }

    private List<List<String>> getContentTitleList(List<List<Chapter>> parts) {
        List<List<String>> contentTitleList = new ArrayList<>();
        for (List<Chapter> p : parts
                ) {
//            System.out.println("p begin");
            List<String> temp = new ArrayList<>();
            for (Chapter c : p) {
                temp.add(c.getTitle());
                System.out.println(c.getTitle());
            }
            contentTitleList.add(temp);
        }
        return contentTitleList;
    }
}

class DownloadCallable implements Callable<String> {
    private NovelContent novelContent;
    private HashMap<String, String> contentMap;

    public DownloadCallable(NovelContent novelContent, HashMap<String, String> contentMap) {
        this.novelContent = novelContent;
        this.contentMap = contentMap;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(novelContent.getTitle() + "\r\n").append(novelContent.getContent().replaceAll("<p>", "")
                .replaceAll("</p>", "\r\n")).append("\r\n").append("\r\n");
//        System.out.println(novelContent.getTitle()+sb.length());
        contentMap.put(novelContent.getTitle(), sb.toString());
        return novelContent.getTitle() + "完成";
    }

}