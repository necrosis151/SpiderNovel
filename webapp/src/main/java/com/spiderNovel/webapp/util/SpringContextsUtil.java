package com.spiderNovel.webapp.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import sun.applet.Main;

import javax.annotation.Resource;

@Component
public class SpringContextsUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public <T> T getBean(String beanName, Class<T> clazs) {
        return clazs.cast(getBean(beanName));
    }

//    public static void main(String[] args) {
//        SpringContextsUtil springContextsUtil = new SpringContextsUtil();
//        System.out.println("test");
//        System.out.println("ttt");
//        System.out.println(springContextsUtil.getBean("novelIndexServerImpl"));
//
//    }
}
