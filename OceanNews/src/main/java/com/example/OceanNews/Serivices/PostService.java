package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post create(Post addPost);

    Iterable<Post> getAllPost();
    Post postUpdate(Long id);
    void postDelete(Long ID);
    List <Post> articleStatus(String postStatus);

    boolean updatePost(Long id, Post addPost);
}
