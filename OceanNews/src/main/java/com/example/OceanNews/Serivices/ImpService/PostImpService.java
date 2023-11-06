package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Post;
import com.example.OceanNews.Repo.PostRepo;
import com.example.OceanNews.Serivices.PostService;
import jakarta.el.ELException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImpService implements PostService {
    @Autowired
    PostRepo addPostRepo;
    @Override
    public Post create(Post addPost) {
        return  addPostRepo.save(addPost);
    }

    @Override
    public Iterable<Post> getAllPost() {
        return addPostRepo.findAll();
    }
    @Override
    public void postDelete(Long id) {
        Boolean existPost=addPostRepo.existsById(id);
        if (existPost){
            addPostRepo.deleteById(id);
        }
    }

    @Override
    public List<Post> articleStatus(String postStatus) {
        return addPostRepo.findBypostStatus(postStatus);
    }

    @Override
    public boolean updatePost(Long id, Post postUpdate) {
        Post existingPost= addPostRepo.findByPostID(id);
        assert existingPost != null;
        existingPost.setPostTitle(postUpdate.getPostTitle());
        existingPost.setPostContent(postUpdate.getPostContent());
        existingPost.setCreator(postUpdate.getCreator());
        addPostRepo.save(existingPost);
        return false;
    }

    @Override
    public Boolean checkIdExists(Long id) {
        Boolean existsId=addPostRepo.existsById(id);
        return existsId;
    }

    @Override
    public Post findId(Long id) {
        return addPostRepo.findByPostID(id);
    }

}