package com.novel.serverimpl;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AbstractSpider {
    /*
     * 从URL中获取html文本
     * */
    public String getHtml(String url, String charset) {
        String result = "";
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        CloseableHttpResponse httpResponse = null;
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
        HttpGet request = new HttpGet(url);

        try {
            CloseableHttpResponse httpResponse = client.execute(request);
//            httpResponse = httpClient.execute(new HttpGet(url));
            result = EntityUtils.toString(httpResponse.getEntity(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
