package com.github.lit.download;

import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;

/**
 * @author liulu
 * @version v1.0
 * date 2019-01-20 14:57
 */
public class Application {

    public static void main(String[] args) throws IOException {

        String downloadUrl = "https://dldir1.qq.com/qqfile/qq/QQ9.0.9/24439/QQ9.0.9.24439.exe";


        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        headers.setRange(Collections.singletonList(HttpRange.createByteRange(0, 1L)));
//        RequestEntity requestEntity = new RequestEntity()
        HttpEntity httpEntity = new HttpEntity(headers);
        long l = System.currentTimeMillis();
        URL url = new URL(downloadUrl);
        System.out.println(url);
        URLConnection connection = url.openConnection();

        System.out.println(System.currentTimeMillis() - l);

        l = System.currentTimeMillis();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

        System.out.println(System.currentTimeMillis() - l);



        l = System.currentTimeMillis();
        ResponseEntity<byte[]> entity = restTemplate.exchange(downloadUrl, HttpMethod.GET, httpEntity, byte[].class);
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(entity);



        l = System.currentTimeMillis();
//        ResponseEntity<byte[]> forEntity = restTemplate.getForEntity(downloadUrl, byte[].class);

        System.out.println(System.currentTimeMillis() - l);

//        System.out.println(forEntity);

    }



}
