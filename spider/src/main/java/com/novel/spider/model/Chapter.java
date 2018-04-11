package com.novel.spider.model;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String title;
    private String url;
    private boolean vip;

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isVip() {
        return vip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ",vip=" + vip + '}';
    }
}
