package com.spiderNovel.webapp.dao;

import com.novel.storage.model.Novel_Info;

public interface Qidian_Novel_InfoDao {
    int saveOrUpdate(Novel_Info record);

    int deleteByPrimaryKey(Integer id);

    int insert(Novel_Info record);

    int insertSelective(Novel_Info record);

    Novel_Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Novel_Info record);

    int updateByPrimaryKey(Novel_Info record);
}