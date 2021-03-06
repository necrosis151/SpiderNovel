package com.novel.spider.serverimpl;

import com.novel.spider.model.NovelContent;
import com.novel.spider.serverimpl.spiderinterface.ContentSpider;
import com.novel.util.AbstractSpider;
import com.novel.util.SpiderRuleReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public class QidianContentSpider implements ContentSpider {
    @Override
    public NovelContent getContent(String url) {
        HashMap<String, String> SpiderContext = SpiderRuleReader.getSpiderContext(url);
        String result = AbstractSpider.getHtml(url, SpiderContext.get("charset"));
        Document doc = Jsoup.parse(result);
        NovelContent novelContent = new NovelContent();
        Elements noveltitle = doc.select(SpiderContext.get("novel-title"));
        novelContent.setTitle(noveltitle.text());
        Elements Contents = doc.select(SpiderContext.get("novel-content"));
        StringBuilder sb = new StringBuilder();
        for (Element c : Contents
                ) {
            if (c.children().size() == 0) {
                sb.append(c.toString());
            }
        }
        novelContent.setContent(sb.toString());
        Elements readpages = doc.select(SpiderContext.get("novel-readpage"));
        if (readpages.size() == 3) {
            novelContent.setPre("http:"+readpages.get(0).attr("href"));
            novelContent.setIndex("http:"+readpages.get(1).attr("href"));
            novelContent.setNext("http:"+readpages.get(2).attr("href"));
        } else {
            novelContent.setPre("");
            novelContent.setIndex("http:"+readpages.get(0).attr("href"));
            novelContent.setNext("http:"+readpages.get(1).attr("href"));
        }
        return novelContent;

    }
}
