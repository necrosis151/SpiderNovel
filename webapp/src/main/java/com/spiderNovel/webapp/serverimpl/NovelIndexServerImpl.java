package com.spiderNovel.webapp.serverimpl;

import com.github.pagehelper.PageHelper;
import com.novel.storage.model.Novel_Info;
import com.novel.storage.server.StorageServer;
import com.novel.util.SpiderRuleReader;
import com.spiderNovel.webapp.dao.Qidian_Novel_InfoDao;
import com.spiderNovel.webapp.server.NovelIndexServer;
import com.spiderNovel.webapp.util.SpringContextsUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

@Component()

public class NovelIndexServerImpl implements NovelIndexServer {
    private Semaphore semaphore = new Semaphore(1);

    private SpringContextsUtil getSpringContextsUtil() {
        return springContextsUtil;
    }

    private void setSpringContextsUtil(SpringContextsUtil springContextsUtil) {
        this.springContextsUtil = springContextsUtil;
    }

    @Resource
    private SpringContextsUtil springContextsUtil;

    @Override
    public void saveNovelIndex(String url) {
        String next = url;
        if (semaphore.tryAcquire()) {
            while (!next.equals("")) {
                savePage(next);
                next = getNextPage(next);
            }
            semaphore.release();
        } else {
            System.out.println("正在执行中");
        }
    }

    private void savePage(String url) {
        System.out.println("保存" + url);
        HashMap<String, String> spiderContext = SpiderRuleReader.getSpiderContext(url);
        List<Novel_Info> infoList = StorageServer.getNovelInfos(url);
        String beanName = spiderContext.get("novel_infoDao");
        Method method = ReflectionUtils.findMethod(springContextsUtil.getBean(beanName).getClass(), "saveOrUpdate", Novel_Info.class);
        for (Novel_Info n : infoList
                ) {
            ReflectionUtils.invokeMethod(method, springContextsUtil.getBean(beanName), n);
        }
    }

    @Override
    public String getNextPage(String url) {
        return StorageServer.getNextPage(url);
    }

    @Override
    public List<Novel_Info> getNovelList(String site, int pageNum, int pageSize) {
        HashMap<String, String> spiderContext = SpiderRuleReader.getSpiderContext(site);
        List<Novel_Info> infoList = null;
        String beanName = spiderContext.get("novel_infoDao");
        Method method = ReflectionUtils.findMethod(springContextsUtil.getBean(beanName).getClass(), "selectAll");
        PageHelper.startPage(pageNum, pageSize);
        infoList = (List<Novel_Info>) ReflectionUtils.invokeMethod(method, springContextsUtil.getBean(beanName));
        return infoList;
    }

    @Override
    public List<Novel_Info> getNovelListByConten(String site, int pageNum, int pageSize, String content) {
        List<Novel_Info> infoList = new ArrayList<>();
        if (site.equals("all")) {
            for (HashMap<String, String> spiderContext : SpiderRuleReader.getContextMap().values()
                    ) {
                String beanName = spiderContext.get("novel_infoDao");
                Method method = ReflectionUtils.findMethod(springContextsUtil.getBean(beanName).getClass(), "selectByContent", String.class);
                PageHelper.startPage(pageNum, pageSize);
                ArrayList<Novel_Info> temp = (ArrayList<Novel_Info>) ReflectionUtils.invokeMethod(method, springContextsUtil.getBean(beanName), content);
                infoList.addAll(temp);
                if (temp.size() == 10) {
                    break;
                } else {
                    pageSize = pageSize - temp.size();
                }
            }
            return infoList;
        } else {
            HashMap<String, String> spiderContext = SpiderRuleReader.getSpiderContext(site);
            String beanName = spiderContext.get("novel_infoDao");
            Method method = ReflectionUtils.findMethod(springContextsUtil.getBean(beanName).getClass(), "selectByContent", String.class);
            PageHelper.startPage(pageNum, pageSize);
            infoList = (List<Novel_Info>) ReflectionUtils.invokeMethod(method, springContextsUtil.getBean(beanName), content);
            return infoList;
        }
    }

    @Override
    public List<String> getTypeList(String site) {
        String beanName = "";
        for (HashMap<String, String> spiderContext : SpiderRuleReader.getContextMap().values()
                ) {
            if (spiderContext.get("title").contains(site)) {
                beanName = spiderContext.get("novel_infoDao");
                break;
            }
        }
        System.out.println(beanName);
        Method method = ReflectionUtils.findMethod(springContextsUtil.getBean(beanName).getClass(), "selectTypeList");
        System.out.println(method.getName());
        List<String> typeList = (List<String>) ReflectionUtils.invokeMethod(method, springContextsUtil.getBean(beanName));
//        List<String> typeList=qidian_novel_infoDao.selectTypeList();
        return typeList;
    }

    @Override
    public List<Novel_Info> getNovelByType(String site,String type) {

        return null;
    }
}
