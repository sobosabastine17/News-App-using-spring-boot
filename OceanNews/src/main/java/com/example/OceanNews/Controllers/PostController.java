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
    @PutMapping("/post/update/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody Post postUpdate){
        Post checkId=addPostService.findId(id);
        if (checkId!=null){
            addPostService.updatePost(id,postUpdate);
            return ResponseEntity.ok("Post updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with ID:"+id+" can not be found");
    }
    // Delete a post by ID
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        Boolean checkId=addPostService.checkIdExists(id);
       if (checkId.equals(true)){
           addPostService.postDelete(id);
           return ResponseEntity.ok("Post Deleted successfully");
       }
        return ResponseEntity.ok("Post ID "+id+" could not be found to perform the operation");
    }//end of deletePost method
    @GetMapping("/article/{postStatus}")
    public Iterable<Post> postStatus(@PathVariable String postStatus){
        return addPostService.articleStatus(postStatus);
    }//end of postStatus method
}//end of PostController class
