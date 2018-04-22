package com.spiderNovel.webapp.controller;

import com.spiderNovel.webapp.server.NovelIndexServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class TestController {

    public NovelIndexServer getNovelIndexServer() {
        return novelIndexServer;
    }

    public void setNovelIndexServer(NovelIndexServer novelIndexServer) {
        this.novelIndexServer = novelIndexServer;
    }
    @Resource(name = "novelIndexServerImpl")
    private NovelIndexServer novelIndexServer;

    @RequestMapping("test")
    public String gotestpage(String url) {

        novelIndexServer.saveNovelIndex(url);
        return "test";
    }
}
