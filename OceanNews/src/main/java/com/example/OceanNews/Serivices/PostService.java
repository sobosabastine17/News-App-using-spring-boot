package com.example.OceanNews.Serivices;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post add(Post addPost) throws ELException;

    Iterable<Post> getAll();

    void softDelete(Long ID) throws ELException;
    void hardDelete(Long ID) throws ELException;

    List<Post> status(Long postStatus) throws ELException;

    boolean update(Long id, Post addPost) throws ELException;
    void edit(Long id, Post addPost) throws ELException;

    Boolean checkIdExists(Long id) throws ELException;
    Post findId(Long id) throws ELException;
    String restore(Long id) throws ELException;
    Post getById(Long id) throws ELException;
    Iterable<Post> getByCategory(Long id) throws ELException;
    boolean categoryIdExists(Long id) throws ELException;
}