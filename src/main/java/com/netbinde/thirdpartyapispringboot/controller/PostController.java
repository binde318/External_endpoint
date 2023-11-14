package com.netbinde.thirdpartyapispringboot.controller;

import com.netbinde.thirdpartyapispringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
   private PostService postService;

   @GetMapping("/getPosts")
    List<Map<String, Object>> getAllPosts(){

       return postService.getPosts();
   }

    @GetMapping("/getPostsById/{id}")
    Map<String, Object> getPostsById(@PathVariable("id") int id){

       return postService.getPostById(id);
    }

    @PatchMapping("/insertsPosts")
    Map<String, Object> insertsPosts(@RequestBody Map<String,Object> payload){
        return postService.insertsPosts(payload);
    }
    @PutMapping("/updatePosts")
    Map<String, Object> updatePosts(@RequestBody Map<String,Object> payload, int id){
        return postService.updatePosts(payload,id);
    }
    @DeleteMapping("/deletePosts/{id}")
    Map<String, Object> deletePost(@PathVariable("id") int id){
        return postService.deletePost(id);
    }
}
