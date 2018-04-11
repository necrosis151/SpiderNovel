package com.novel.storage.model;

public class Novel_Info {
    private Integer id;

    private String name;

    private String author;

    private String url;

    private String type;

    private String lastupdatechapter;

    private String lastupdatechapterurl;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLastupdatechapter() {
        return lastupdatechapter;
    }

    public void setLastupdatechapter(String lastupdatechapter) {
        this.lastupdatechapter = lastupdatechapter == null ? null : lastupdatechapter.trim();
    }

    public String getLastupdatechapterurl() {
        return lastupdatechapterurl;
    }

    public void setLastupdatechapterurl(String lastupdatechapterurl) {
        this.lastupdatechapterurl = lastupdatechapterurl == null ? null : lastupdatechapterurl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}