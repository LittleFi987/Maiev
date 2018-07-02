package com.test.http;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author chenhao.ych
 * @date 2018-07-01
 */
public class HttpClientTest {


    public static void main(String[] args) {
        send("http://www.begincode.net/");
    }



    private static void send(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String jsonData = null;
        try {
            HttpGet get = new HttpGet(url);
            response = client.execute(get);
            System.out.println("return ------->" + response.getEntity().toString());
            Header[] headers = response.getAllHeaders();
            for(Header head : headers){
                if(head.getValue().equals("application/json;charset=utf-8")){
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        jsonData = EntityUtils.toString(entity, "UTF-8");
                    }
                }
            }
        } catch (IOException e) {
            // ignore
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
