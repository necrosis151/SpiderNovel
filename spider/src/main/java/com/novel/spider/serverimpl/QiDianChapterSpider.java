package com.novel.spider.serverimpl;

import com.novel.spider.model.Chapter;
import com.novel.spider.server.ChapterSpider;
import com.novel.util.SpiderRuleReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QiDianChapterSpider extends AbstractSpider implements ChapterSpider {
//    Map<String, HashMap<String, String>> contexts = SpiderRuleReader.getContextMap();

    public List<List<Chapter>> getChapterByPart(String url, boolean vip) {
        Map<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        List parts = new ArrayList();
        String result = getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(result);

        Elements uls = doc.select(spiderContext.get("chapters-selector-part"));
        for (int i = 0; i < uls.size() - 1; i++) {
            Elements as = uls.get(i).select(spiderContext.get("chapters-selector-chapterlist"));
            List<Chapter> chapters = new ArrayList();
            for (Element a : as
                    ) {
                Chapter chapter = new Chapter();
                chapter.setTitle(a.text());
                String chapterURL = a.attr("href");
                chapter.setUrl("http:" + chapterURL);
                if (chapterURL.contains("vip")) {
                    chapter.setVip(true);
                } else {
                    chapter.setVip(false);
                }
                if (vip==true){
                    chapters.add(chapter);
                }
                if (vip==false&&chapter.isVip()==false){
                    chapters.add(chapter);
                }
            }
            parts.add(chapters);
        }
        return parts;
    }
}
