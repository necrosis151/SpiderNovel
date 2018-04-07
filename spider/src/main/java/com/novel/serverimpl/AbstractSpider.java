package com.novel.serverimpl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AbstractSpider {
    /*
     * ��URL�л�ȡhtml�ı�
     * */
    public String getHtml(String url,String charset) {
        String result = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(new HttpGet(url));
            result = EntityUtils.toString(httpResponse.getEntity(), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
