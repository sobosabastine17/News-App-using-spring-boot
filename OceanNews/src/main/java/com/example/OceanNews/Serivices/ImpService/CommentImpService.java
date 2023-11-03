package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Comment;
import com.example.OceanNews.Repo.CommentRepo;
import com.example.OceanNews.Serivices.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpService implements CommentService {
    @Autowired
    CommentRepo commentRepo;
    @Override
    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public Iterable<Comment> getAllComment() {
        return commentRepo.findAll();
    }
}
