package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Exception.ELException;
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
    public Comment add(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public Iterable<Comment> getAll() {
        return commentRepo.findAll();
    }

    @Override
    public List<Comment> status(Long status) {
       return commentRepo.findAllByStatus(status);
    }

    @Override
    public String update(Long id) {
        boolean existsComment=commentRepo.existsById(id);
        if (existsComment){
            Comment existComment=commentRepo.findById(id).orElseThrow();
            existComment.setStatus(id);
        }
        return "Id "+id+" not found";
    }

    @Override
    public List<Comment> getByPostId(Long id)throws ELException {
        if (!commentRepo.existsById(id)){
            throw new ELException("Id: "+id+" not found");
        }
        return commentRepo.findAllByPostId(id);
    }
}
