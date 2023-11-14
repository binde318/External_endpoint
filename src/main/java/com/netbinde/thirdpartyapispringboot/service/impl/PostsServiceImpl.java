package com.netbinde.thirdpartyapispringboot.service.impl;

import com.netbinde.thirdpartyapispringboot.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Service
public class PostsServiceImpl implements PostService {
    String baseUrl = "https://jsonplaceholder.typicode.com/";
    StringBuilder stringBuilder = new StringBuilder(baseUrl);
String POST = "/posts";
String POSTSBYID = "/posts/";
@Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Map<String, Object>> getPosts() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders());
       String url = stringBuilder.append(POST).toString();
       val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
       return response.getBody();
    }

    @Override
    public Map<String, Object> getPostById(int id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders());
        String url = stringBuilder.append(POSTSBYID).append(id).toString();
        val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> insertsPosts(Map<String, Object> payload) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, gethttpHeaders());
        String url = stringBuilder.append(POST).toString();
        val response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> updatePosts(Map<String, Object> payload, int id) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, gethttpHeaders());
        String url = stringBuilder.append(POSTSBYID).append(id).toString();
        val response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> deletePost(int id) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(gethttpHeaders());
        String url = stringBuilder.append(POSTSBYID).append(id).toString();
        val response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Map.class);
        return response.getBody();
    }

    HttpHeaders gethttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;

    }
}
