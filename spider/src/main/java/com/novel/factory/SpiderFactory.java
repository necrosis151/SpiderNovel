package com.novel.factory;

import com.novel.util.NovelSpiderUtil;

import java.util.HashMap;
import java.util.Map;

public class SpiderFactory {
    public Object getChapterSpider(String url) {
        HashMap<String, String> SpiderContext = NovelSpiderUtil.getSpiderContext(url);
        if (SpiderContext==null){
            throw new RuntimeException("不支持的网页");
        }
        String spider = SpiderContext.get("spider");
        try {
            if (spider == null || spider.equalsIgnoreCase("default") || spider.equals("")) {
                return Class.forName("com.novel.serverimpl.DefaultChapterSpider").newInstance();
            } else {
                return Class.forName(spider).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        SpiderFactory sf=new SpiderFactory();
//       boolean a= sf.getChapterSpider("qidian.com") instanceof IChapter;
//        System.out.println(a);
//    }

}



