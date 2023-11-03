package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Post;
import com.example.OceanNews.Repo.PostRepo;
import com.example.OceanNews.Serivices.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImpService implements PostService {
    @Autowired
    PostRepo addPostRepo;
    @Override
    public Post create(Post addPost) {
       return addPostRepo.save(addPost);
    }

    @Override
    public Iterable<Post> getAllPost() {
        return addPostRepo.findAll();
    }

    @Override
    public Post postUpdate(Long id) {
        return addPostRepo.findById(id).orElse(null);
    }
    @Override
    public void postDelete(Long ID) {
        addPostRepo.deleteById(ID);
    }

    @Override
    public List<Post> articleStatus(String postStatus) {
        return addPostRepo.findBypostStatus(postStatus);
    }

    @Override
    public boolean updatePost(Long id, Post postUpdate) {
        Post existingPost= addPostRepo.findById(id).orElseThrow(
                ()-> new IllegalStateException("ID not "+id+" not found")
        );
        //return ResponseEntity.notFound().build();
        assert existingPost != null;
        existingPost.setPostTitle(postUpdate.getPostTitle());
        existingPost.setPostContent(postUpdate.getPostContent());
        existingPost.setCreator(postUpdate.getCreator());
        addPostRepo.save(postUpdate);
        return false;
    }

}