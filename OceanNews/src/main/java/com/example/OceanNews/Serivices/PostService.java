package com.example.OceanNews.Serivices;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post create(Post addPost) throws ELException;

    Iterable<Post> getAllPost();

    void postDelete(Long ID) throws ELException;

    List<Post> articleStatus(String postStatus) throws ELException;

    boolean updatePost(Long id, Post addPost) throws ELException;

    Boolean checkIdExists(Long id) throws ELException;
    Post findId(Long id) throws ELException;
}