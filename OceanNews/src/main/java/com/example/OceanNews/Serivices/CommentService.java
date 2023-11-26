package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment addComment(Comment comment);
    Iterable<Comment> getComment();
    List<Comment> commentStatus(String status);
    String updateComment(Long id);
}
