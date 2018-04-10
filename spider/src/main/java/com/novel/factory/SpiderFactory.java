package com.novel.factory;

import com.novel.util.NovelSpiderUtil;

import java.util.HashMap;

public class SpiderFactory {
    public static Object getChapterSpider(String url) {
        HashMap<String, String> SpiderContext = NovelSpiderUtil.getSpiderContext(url);
        if (SpiderContext==null){
            throw new RuntimeException("��֧�ֵ���ҳ");
        }
        String spider = SpiderContext.get("ChapterSpider");
        try {
            if (spider == null || spider.equalsIgnoreCase("default") || spider.equals("")) {
                return Class.forName("com.novel.serverimpl.DefaultSpider").newInstance();
            } else {
                return Class.forName(spider).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object getContentSpider(String url) {
        HashMap<String, String> SpiderContext = NovelSpiderUtil.getSpiderContext(url);
        if (SpiderContext==null){
            throw new RuntimeException("��֧�ֵ���ҳ");
        }
        String spider = SpiderContext.get("ContentSpider");
        try {
            if (spider == null || spider.equalsIgnoreCase("default") || spider.equals("")) {
                return Class.forName(spider).newInstance();
            } else {
                return Class.forName("com.novel.serverimpl.ZhuLangContentSpider").newInstance();
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



