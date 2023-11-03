package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Post;
import com.example.OceanNews.Serivices.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    PostService addPostService;

    @PostMapping("/post/add_post")
    public ResponseEntity<String> createPost(@RequestBody Post addPost){
        if (addPostService.create(addPost)!=null){
            return ResponseEntity.ok("Post successfully sent\n");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post could not be sent");
    }

    @GetMapping("/post/get_post")
    public Iterable<Post> getPost(){
        return addPostService.getAllPost();
    }
    @GetMapping("/post/myApp")
    public String testing(){
        return "Welcome to Ocean News";
    }
    @PutMapping("/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody Post postUpdate){
        //addPostService.updatePost(id,postUpdate);
        if (!addPostService.updatePost(id,postUpdate)){
            return ResponseEntity.ok("Post updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID fake not found");
    }
    // Delete a post by ID
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        Post existingPost = addPostService.postUpdate(id);

        if (existingPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }

        addPostService.postDelete(existingPost.getPostID());
        //return ResponseEntity.noContent().build();
        return ResponseEntity.ok("Post Deleted successfully");
    }
    @GetMapping("/article/{postStatus}")
    // long status 1 representing published articles
    public Iterable<Post> publishedArticles(@PathVariable String postStatus){
        return addPostService.articleStatus(postStatus);
    }

    //*************************************//
    //....end of add post Mapping Request...
    //*************************************//
}
