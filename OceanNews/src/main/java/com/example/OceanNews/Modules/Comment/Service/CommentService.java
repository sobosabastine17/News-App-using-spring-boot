package com.example.OceanNews.Modules.Comment.Service;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Modules.Comment.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment add(Comment comment);
    Iterable<Comment> getAll();
    List<Comment> status(Long status)throws ELException;
    String update(Long id);
    List<Comment> getByPostId(Long id)throws ELException;
}
