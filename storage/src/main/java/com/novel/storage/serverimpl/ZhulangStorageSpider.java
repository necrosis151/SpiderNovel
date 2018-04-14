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

public class ZhulangStorageSpider implements StorageSpider {
    private HashMap<String, String> spiderContext = null;
    @Override
    public List<Novel_Info> getInfos(String url) {
        spiderContext =  SpiderRuleReader.getSpiderContext(url);
        String html = AbstractSpider.getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(html);
        Elements novel_infos = doc.select(spiderContext.get("novel_info"));
        Novel_Info novel = null;
        List<Novel_Info> infoList = new ArrayList<>();
        Elements tds = null;
        for (Element n : novel_infos
                ) {
            novel = new Novel_Info();
            tds = n.select("td");
            novel.setId(Integer.parseInt(tds.get(0).text()));
            novel.setAuthor(tds.get(2).select("a").text());
            novel.setName(tds.get(1).select("a").get(1).text());
            novel.setUrl(tds.get(1).select("a").get(1).attr("href"));
            novel.setType(tds.get(1).select("a").get(0).text());
            novel.setStatus("未知");
            infoList.add(novel);
        }

        return infoList;
    }

    @Override
    public String getNextPage(String url) {
        spiderContext = SpiderRuleReader.getSpiderContext(url);
        String html = AbstractSpider.getHtml(url, spiderContext.get("charset"));
        Document doc = Jsoup.parse(html);
//        doc.setBaseUri("http://www.zhulang.com");
        String temp = doc.select("div[class=page] a[class=next]").attr("href");
        if (temp.equals("")){
            return "";
        }
//        System.out.println(temp);
        return  "http://www.zhulang.com"+temp;
    }
}
