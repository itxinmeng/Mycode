package cn.itxinmeng.utils;

import cn.itxinmeng.pojo.Page;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**心梦
 * 下载网页内容的工具类
 */

public class HttpUtils {
    /**
     * 反爬虫 屏蔽
     * @param page
     * @return
     */
    public static String getHtmlContent(Page page) {
        String pageContent = null;
        try {
            //创建httpClient对象
            HttpClient httpClient = HttpClientBuilder.create().build();

            //通过get请求方式请求商品详情页面
            HttpGet httpGet = new HttpGet(page.getUrl());

            //执行get请求，得到一个响应对象
            HttpResponse response = httpClient.execute(httpGet);

            //得到响应的实体对象
            HttpEntity httpEntity = response.getEntity();
            pageContent = EntityUtils.toString(httpEntity);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageContent;
    }

    /**
     * 防止屏蔽
     * @param url
     * @return
     */
    public static String getDataByUrl(String url) {
        String data = "";
        try {
            URL targetUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line = "";

                while ((line = reader.readLine()) != null) {
                    data += line + "\n";

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
