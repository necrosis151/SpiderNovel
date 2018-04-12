package com.novel.util;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AbstractSpider {

    public static String getHtml(String url, String charset) {
        String result = "";
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        CloseableHttpResponse httpResponse = null;
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
        CloseableHttpResponse httpResponse = null;
        try {
            for (int i = 1; i <= 5; i++) {
                try {
                    httpResponse = httpClient.execute(new HttpGet(url));
                    break;
                }catch (Exception e){
                    System.err.println(url+"尝试第[" + i +  "]次下载失败了！");
                }
            }
            result = EntityUtils.toString(httpResponse.getEntity(), charset);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
