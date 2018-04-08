package com.novel.serverimpl;

import com.novel.model.Chapter;
import com.novel.server.ChapterSpider;
import com.novel.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhuLangChapterSpider extends AbstractSpider implements ChapterSpider {
    @Override
    public List<List<Chapter>> getChapterByPart(String url) {
        Map<String, String> spiderContext = NovelSpiderUtil.getSpiderContext(url);
        List parts = new ArrayList();
        String result = getHtml(url, spiderContext.get("charset"));
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
                } else {
                    chapter.setVip(false);
                }
                chapters.add(chapter);
            }
            parts.add(chapters);
        }
        return parts;
    }
}
