package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Comment;
import com.example.OceanNews.Serivices.CommentService;
import com.example.OceanNews.Serivices.ImpService.CommentImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsApp/v1/comment")
public class CommentController {
    @Autowired
    CommentService commentService=new CommentImpService();

    //save comment mapping request
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Comment comment)
    {
        commentService.add(comment);
        return ResponseEntity.ok("Comment sent");
    }
    //get all comment mapping request
    @GetMapping("/allComment")
    public Iterable<Comment> getComment(){
        return commentService.getAll();
    }
    //get comment by post id mapping request
    @GetMapping("/post/{id}")
    public Iterable<Comment> getByPostId(@PathVariable Long id){
        return commentService.getByPostId(id);
    }
    //update comment mapping request
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id){
        commentService.update(id);
        return ResponseEntity.ok("Comment id: "+id+" updated");
    }
    @GetMapping("/status/{id}")
    public Iterable<Comment> status(@PathVariable Long id){
        return commentService.status(id);
    }
    //*************************************//
    //....end of comment Mapping Request...
    //*************************************//

}
