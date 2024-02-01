package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Post;
import com.example.OceanNews.Serivices.ImpService.PostImpService;
import com.example.OceanNews.Serivices.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsApp/v1")
public class PostController {
    @Autowired
    PostService addPostService=new PostImpService();

    @PostMapping("/post/add")
    public ResponseEntity<String> post(@RequestBody Post addPost){
        if (addPostService.add(addPost)!=null){
            return ResponseEntity.ok("Post successfully sent\n");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post could not be sent");
    }
    @PatchMapping("/post/edit/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody Post postUpdate){
        Post checkId=addPostService.findId(id);
        if (checkId!=null){
            addPostService.edit(id,postUpdate);
            return ResponseEntity.ok("Post updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with ID:"+id+" can not be found");
    }
    @GetMapping("post/get_post")
    public Iterable<Post> getAll(){
        return addPostService.getAll();
    }
    @GetMapping("/post/getById/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Post checkId=addPostService.findId(id);
        if (checkId!=null){
            return ResponseEntity.ok(checkId);
        }
        return ResponseEntity.badRequest().body("Post with id: "+id+" does not exist");
    }
    // Delete a post by changing the status to 0
    @DeleteMapping("/post/delete/soft/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Long id) {
       if (addPostService.checkIdExists(id)){
           addPostService.softDelete(id);
           return ResponseEntity.ok("Post Deleted successfully");
       }
        return ResponseEntity.ok("Post ID "+id+" could not be found to perform the operation");
    }
    // Delete a post by changing the status to 0
    @DeleteMapping("/post/delete/hard/{id}")
    public ResponseEntity<String> hardDelete(@PathVariable Long id) {
        if (addPostService.checkIdExists(id)){
            addPostService.hardDelete(id);
            return ResponseEntity.ok("Post Deleted successfully");
        }
        return ResponseEntity.ok("Post ID "+id+" could not be found to perform the operation");
    }
    @GetMapping("/post/status/{postStatus}")
    public Iterable<Post> status(@PathVariable Long postStatus){
        return addPostService.status(postStatus);
    }//end of postStatus method
    @PatchMapping("/post/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Post postUpdate){
        if (addPostService.update(id,postUpdate)){
            return ResponseEntity.ok("Post updated successfully");
        }
        return ResponseEntity.badRequest().body("Post with ID:"+id+" can not be found");
    }
    @PatchMapping("/post/restore/{id}")
    public ResponseEntity<String> restore(@PathVariable Long id){
        if (addPostService.findId(id)!=null){
            addPostService.restore(id);
            return ResponseEntity.ok("Post restored successfully");
        }
        return ResponseEntity.badRequest().body("Post with ID:"+id+" can not be found");
    }
}//end of PostController class
