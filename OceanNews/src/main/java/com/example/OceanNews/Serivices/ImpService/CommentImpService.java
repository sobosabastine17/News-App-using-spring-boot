package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Comment;
import com.example.OceanNews.Repo.CommentRepo;
import com.example.OceanNews.Serivices.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentImpService implements CommentService {
    @Autowired
    CommentRepo commentRepo;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public Iterable<Comment> getComment() {
        return commentRepo.findAll();
    }

    @Override
    public List<Comment> commentStatus(String status) {
       //return commentRepo.findAllByStatus(status);
        return null;
    }

    @Override
    public String updateComment(Long id) {
        boolean existsComment=commentRepo.existsById(id);
        if (existsComment){
            Comment existComment=commentRepo.findById(id).orElseThrow();
            existComment.setStatus(id);
        }
        return "Id "+id+" not found";
    }
}
