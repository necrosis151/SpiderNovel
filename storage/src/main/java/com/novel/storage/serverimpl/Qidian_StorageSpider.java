package com.novel.storage.serverimpl;

import com.novel.storage.model.Novel_Info;
import com.novel.storage.serverimpl.storageInterface.StorageSpider;
import com.novel.util.AbstractSpider;
import com.novel.util.SpiderRuleReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Qidian_StorageSpider implements StorageSpider {
    private HashMap<String, String> spiderContext = null;

    @Override
    public List<Novel_Info> getInfo(String url) {
        spiderContext = SpiderRuleReader.getSpiderContext(url);
        String html = AbstractSpider.getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(html);
        Elements novel_info_list = doc.select(spiderContext.get("novel_info"));
        Novel_Info novel = new Novel_Info();
        List<Novel_Info> infoList = new ArrayList<>();
        Elements temp = null;
        for (Element n : novel_info_list
                ) {
            temp = n.select("h4");
            novel.setName(temp.text());
            novel.setUrl("http:" + temp.select("a").attr("href"));
//            System.out.println(novel.getUrl());
            novel.setId(Integer.parseInt(novel.getUrl().substring(novel.getUrl().lastIndexOf("/") + 1)));
            temp = n.select("p[class=author] a");
            novel.setAuthor(temp.get(0).text());
            novel.setType(temp.get(1).text());
            novel.setStatus(temp.get(2).select("span").text());
            infoList.add(novel);
        }
        return infoList;
    }

    @Override
    public String getNextPage(String url) {
        spiderContext = SpiderRuleReader.getSpiderContext(url);
        String html = AbstractSpider.getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(html);
        String temp = doc.select("a[class=lbf-pagination-next]").attr("href");
        if (temp.equals("javascript:;")) {
            return "";
        } else {
            return "http:" + temp;
        }

    }


}
