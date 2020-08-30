package com.side.project.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpRequest{
    /**
     *  공공데이터 request 함수로서, url, serviceKey, parameter를 인자로 받는다.
     *
     *  url : 공공데이터 포털에 요청을 보내는 주소
     *  serviceKey : 해당 서비스키
     *  parameter : url에 요청에 필요한 파라미터를 맵 형식으로 받는다.
     */
    public Object publicDataRequest(Url requestUrl, Apikey serviceKey, Map<String, String> parameter) throws Exception{
        StringBuilder request = new StringBuilder();

        request.append(requestUrl.getUrl())
                .append("&serviceKey=")
                .append(serviceKey.getKey())
                .append("&_type=json");

        URL url = new URL(setParameter(request, parameter));

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // 연결 타임아웃 설정
        conn.setConnectTimeout(60000); // 60초
        // 읽기 타임아웃 설정
        conn.setReadTimeout(60000); // 60초

        System.out.println("getResponseCode():"    + conn.getResponseCode());
        System.out.println("getResponseMessage():" + conn.getResponseMessage());

        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        line = in.readLine();
        return line;
    }

    public String setParameter(StringBuilder request, Map<String, String> parameter){
        Set set = parameter.keySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()){
            String key = (String)iterator.next();
            request.append("&").append(key).append("=").append(parameter.get(key));
        }

        return request.toString();
    }
}
