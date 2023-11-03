package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.Comment;
import com.example.OceanNews.Repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment saveComment(Comment comment);
    Iterable<Comment> getAllComment();
}
