package javaTest;

import com.novel.storage.model.Novel_Info;
import com.spiderNovel.webapp.serverimpl.TestServerimpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class Zhulang_Novel_InfoDaoTest {
    @Test
    public void insertTest() {
        Novel_Info info=new Novel_Info();
        int id=2;
        info.setId(id);
        info.setAuthor("test"+id);
        info.setName("test"+id);
        info.setStatus("test"+id);
        info.setType("test"+id);
        info.setUrl("test"+id);

        ApplicationContext act=new ClassPathXmlApplicationContext("spring-mybatis.xml");
        TestServerimpl testServerimpl= (TestServerimpl) act.getBean("testServerimpl");
        testServerimpl.insertTest(info);

    }
}