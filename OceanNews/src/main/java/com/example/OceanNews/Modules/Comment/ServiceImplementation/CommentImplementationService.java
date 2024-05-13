package com.example.OceanNews.Modules.Comment.ServiceImplementation;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Modules.Comment.Comment;
import com.example.OceanNews.Modules.Comment.Repository.CommentRepository;
import com.example.OceanNews.Modules.Comment.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentImplementationService implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> status(Long status) throws ELException{
        if (status >=0 && status<=1){
            if (commentRepository.countByStatus(status)==0){
                throw new ELException("Status ID: "+status+" is empty");
            }
            return commentRepository.findAllByStatus(status);
        }
        throw new ELException("Status must be 0 or 1");
    }

    @Override
    public String update(Long id) {
        boolean existsComment= commentRepository.existsById(id);
        if (existsComment){
            Comment existComment= commentRepository.findById(id).orElseThrow();
            existComment.setStatus(id);
        }
        return "Id "+id+" not found";
    }
    @Override
    public List<Comment> getByPostId(Long id)throws ELException {
        if (commentRepository.existsById(id)){
            //check if post id exists
            if (commentRepository.countByPostId(id)==0){
                throw new ELException("Post id: "+id+" is empty");
            }
            return commentRepository.findAllByPostId(id);
            }
            throw new ELException("Id: "+id+" not found");
        }
}
