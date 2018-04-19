package javaTest;

import com.novel.storage.model.Novel_Info;
import com.spiderNovel.webapp.dao.Qidian_Novel_InfoDao;
import com.spiderNovel.webapp.serverimpl.NovelIndexServerImpl;
import com.spiderNovel.webapp.serverimpl.TestServerimpl;
import com.spiderNovel.webapp.util.SpringContextsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class Zhulang_Novel_InfoDaoTest {
    @Test
    public void insertTest() {
//        Novel_Info info=new Novel_Info();
//        int id=1;
//        info.setId(id);
//        info.setAuthor("test"+id);
//        info.setName("test"+id);
//        info.setStatus("test"+id);
//        info.setType("test"+id);
//        info.setUrl("test"+id);
//        String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page=43995";
        String url = "http://www.zhulang.com/Shuku/index/main/all/sub/all/size/0/flag/0/time/0/type/0/order/0/ini/0/p/1186.html";
        //        TestServerimpl testServerimpl= (TestServerimpl) act.getBean("testServerimpl");
//        testServerimpl.insertTest(info);
        ApplicationContext act = new ClassPathXmlApplicationContext("spring-mybatis.xml");
              NovelIndexServerImpl novelIndexServer = (NovelIndexServerImpl) act.getBean("novelIndexServerImpl");
        novelIndexServer.saveNovelIndex(url);
//        Qidian_Novel_InfoDao dao= (Qidian_Novel_InfoDao) act.getBean("qidian_Novel_InfoDao");
//        System.out.println(dao);
    }


}