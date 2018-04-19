package com.spiderNovel.webapp.serverimpl;

import com.novel.storage.model.Novel_Info;
import com.novel.storage.server.StorageServer;
import com.novel.util.SpiderRuleReader;
import com.spiderNovel.webapp.server.NovelIndexServer;
import com.spiderNovel.webapp.util.SpringContextsUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@Component
@Lazy(false)

public class NovelIndexServerImpl implements NovelIndexServer {
    public SpringContextsUtil getSpringContextsUtil() {
        return springContextsUtil;
    }

    public void setSpringContextsUtil(SpringContextsUtil springContextsUtil) {
        this.springContextsUtil = springContextsUtil;
    }

    @Resource
    private SpringContextsUtil springContextsUtil;

    @Override
    public void saveNovelIndex(String url) {
        HashMap<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        List<Novel_Info> infoList = StorageServer.getNovelInfos(url);
        String beanName = spiderContext.get("novel_infoDao");
        System.out.println(springContextsUtil.getBean(beanName).getClass());
        Method method = ReflectionUtils.findMethod(springContextsUtil.getBean(beanName).getClass(), "insert", Novel_Info.class);
        for (Novel_Info n : infoList
                ) {
            ReflectionUtils.invokeMethod(method, springContextsUtil.getBean(beanName), n);
        }
    }

    @Override
    public String getNextPage(String url) {
        return null;
    }
}
