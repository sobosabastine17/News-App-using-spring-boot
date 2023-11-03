package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Comment;
import com.example.OceanNews.Serivices.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    //save comment mapping request
    @PostMapping("/comment/save")
    public Comment saveComment(@RequestBody Comment comment){
        return commentService.saveComment(comment);
    }
    //get all comment mapping request
    @GetMapping("/comment/allComment")
    public Iterable<Comment> getComment(){
        return commentService.getAllComment();
    }
    //*************************************//
    //....end of comment Mapping Request...
    //*************************************//

}
