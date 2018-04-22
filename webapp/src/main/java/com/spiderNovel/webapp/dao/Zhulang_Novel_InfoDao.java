package com.spiderNovel.webapp.dao;

import com.novel.storage.model.Novel_Info;

import java.util.List;

public interface Zhulang_Novel_InfoDao {
    int saveOrUpdate(Novel_Info record);

    int deleteByPrimaryKey(Integer id);

    int insert(Novel_Info record);

    int insertSelective(Novel_Info record);

    Novel_Info selectByPrimaryKey(Integer id);

    List<Novel_Info> selectAll();

    List<String> selectTypeList();

    List<Novel_Info> selectByContent(String content);

    int updateByPrimaryKeySelective(Novel_Info record);

    int updateByPrimaryKey(Novel_Info record);

    List<Novel_Info> selectNovelByType(String type);
}