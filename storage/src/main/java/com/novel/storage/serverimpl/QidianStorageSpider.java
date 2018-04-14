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

public class QidianStorageSpider implements StorageSpider {
    private HashMap<String, String> spiderContext = null;

    @Override
    public List<Novel_Info> getInfos(String url) {
        spiderContext =  SpiderRuleReader.getSpiderContext(url);
        String html = AbstractSpider.getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(html);
        Elements novel_infos = doc.select(spiderContext.get("novel_info"));
        Novel_Info novel = null;
        List<Novel_Info> infoList = new ArrayList<>();
        Elements temp = null;
        for (Element n : novel_infos
                ) {
            novel = new Novel_Info();
            temp = n.select("h4");
            novel.setName(temp.text());
            url = temp.select("a").attr("href");
            novel.setUrl("http:" + url + "#Catalog");
//            System.out.println(novel.getUrl());
            novel.setId(Integer.parseInt(url.substring(url.lastIndexOf("/") + 1)));
            temp = n.select("p[class=author]");
            novel.setAuthor(temp.select("a").get(0).text());
            novel.setType(temp.select("a").get(2).text());
            novel.setStatus(temp.select("span").text());
            infoList.add(novel);
        }
        return infoList;
    }

    @Override
    public String getNextPage(String url) {
        spiderContext = SpiderRuleReader.getSpiderContext(url);
        String html = AbstractSpider.getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(html);
        String temp = doc.select("a[class=lbf-pagination-page  lbf-pagination-current]").attr("href");
        if (temp.equals("javascript:;")){
            return "";
        }
        Elements as = doc.select("ul[class=lbf-pagination-item-list] a");
        for (int i = 0; i < as.size() - 1; i++) {
            if (temp.equals(as.get(i).attr("href"))) {
                temp = as.get(i + 1).attr("href");
                break;
            }
        }
        return "http:" + temp;
    }


}
