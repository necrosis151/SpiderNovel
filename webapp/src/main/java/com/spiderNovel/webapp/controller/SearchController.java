package com.spiderNovel.webapp.controller;

import com.novel.storage.model.Novel_Info;
import com.spiderNovel.webapp.server.NovelIndexServer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/search")
public class SearchController {
    public NovelIndexServer getNovelIndexServer() {
        return novelIndexServer;
    }

    public void setNovelIndexServer(NovelIndexServer novelIndexServer) {
        this.novelIndexServer = novelIndexServer;
    }

    @Resource
    private NovelIndexServer novelIndexServer;

    @RequestMapping("/novelList")
    @ResponseBody
    public List<Novel_Info> getNovelList(@RequestBody Map<String, String> map) {
        String site = map.get("site");
        int pageNum = Integer.parseInt(map.get("pageNum"));
        int pageSize = Integer.parseInt(map.get("pageSize"));
        String content = map.get("content");
        List<Novel_Info> infoList = novelIndexServer.getNovelListByConten(site, pageNum, pageSize, content);
        return infoList;
    }

    @RequestMapping("/typeList")
    @ResponseBody
    public List<String> getTypeList(@RequestBody Map<String, String> map) {
        String site = map.get("site");
        return novelIndexServer.getTypeList(site);
    }

    @RequestMapping("/byType")
    @ResponseBody
    public List<Novel_Info> getNovelByType(@RequestBody Map<String, String> map) {
        String site = map.get("site");
        String type = map.get("type");
        return novelIndexServer.getNovelByType(site, type);
    }
}
