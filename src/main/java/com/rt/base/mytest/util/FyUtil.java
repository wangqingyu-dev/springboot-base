package com.rt.base.mytest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class FyUtil {

    public  static String updateZhName(String value){
        // 有道翻译接口
        String url = "http://fanyi.youdao.com/openapi.do?keyfrom=xinlei&key=759115437&type=data&doctype=json&version=1.1&q=";
        // 查询出所有的英文国家名字

        // httpclient
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // 翻译每个国家的名字，并且更新数据库

            HttpGet request = new HttpGet(url + URLEncoder.encode(value));
//        HttpGet request = new HttpGet(url + URLEncoder.encode("my name"));
            try {
                CloseableHttpResponse response = client.execute(request);
                String str = EntityUtils.toString(response.getEntity(), "utf-8");
                JSONObject jsonObject = JSON.parseObject(str);
                // 取出json字符串中数组的值
                return (String) jsonObject.getJSONArray("translation").get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }

}

