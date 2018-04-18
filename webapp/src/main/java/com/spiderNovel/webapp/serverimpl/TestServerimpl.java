package com.spiderNovel.webapp.serverimpl;

import com.novel.storage.model.Novel_Info;
import com.spiderNovel.webapp.dao.Zhulang_Novel_InfoDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestServerimpl {
    public Zhulang_Novel_InfoDao getDao() {
        return dao;
    }

    public void setDao(Zhulang_Novel_InfoDao dao) {
        this.dao = dao;
    }

    @Resource
    private Zhulang_Novel_InfoDao dao;

    public void insertTest(Novel_Info novel_info){
        dao.insert(novel_info);
    }
}
