package com.ych.util;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Slf4j
public class HttpClient {


    public static <T> T send(String url, TypeReference<T> typeReference) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        T value = null;
        log.info("[httpclient-get] request url: [{}], className: [{}]", url, typeReference.getClass().getName());
        String jsonData = null;
        try {
            HttpGet get = new HttpGet(url);
            response = client.execute(get);
            Header[] headers = response.getAllHeaders();
            for(Header head : headers){
                if(head.getValue().equals("application/json;charset=utf-8")){
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        jsonData = EntityUtils.toString(entity, "UTF-8");
                    }
                    value = JsonUtils.decode(jsonData.toString(), typeReference);
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
        return value;
    }

}
