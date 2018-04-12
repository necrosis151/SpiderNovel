package com.novel.spider.serverimpl;

import com.novel.spider.model.Chapter;
import com.novel.spider.serverimpl.spiderinterface.ChapterSpider;
import com.novel.util.AbstractSpider;
import com.novel.util.SpiderRuleReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhuLangChapterSpider implements ChapterSpider {
    @Override
    public List<List<Chapter>> getChapterByPart(String url, boolean vip) {
        Map<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        List parts = new ArrayList();
        String result = AbstractSpider.getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(result);
        Elements uls = doc.select(spiderContext.get("chapters-selector-part"));
        for (int i = 0; i < uls.size(); i++) {
            Elements as = uls.get(i).select(spiderContext.get("chapters-selector-chapterlist"));
            List<Chapter> chapters = new ArrayList();
            for (Element a : as
                    ) {
                Chapter chapter = new Chapter();
                chapter.setTitle(a.text());
                String chapterURL = a.attr("href");
                chapter.setUrl(chapterURL);
                if (a.select("span").text().contains("[vip]")) {
                    chapter.setVip(true);
                    if (vip == true) {
                        chapters.add(chapter);
                    }
                } else {
                    chapter.setVip(false);
                        chapters.add(chapter);
                }
            }
            parts.add(chapters);
        }
        return parts;
    }
}
