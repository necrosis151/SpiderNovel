package com.novel.junit;

import com.novel.model.NovelContent;
import com.novel.server.ContentSpider;
import com.novel.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public class QiDianContentSpider extends AbstractSpider implements ContentSpider {
    @Override
    public NovelContent getContent(String url) {
        HashMap<String, String> SpiderContext = NovelSpiderUtil.getSpiderContext(url);
        String result = getHtml(url, SpiderContext.get("charset"));
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

        novelContent.setPre(readpages.get(0).attr("href"));
        novelContent.setIndex(readpages.get(1).attr("href"));
        novelContent.setNext(readpages.get(2).attr("href"));
        return novelContent;

    }
}
