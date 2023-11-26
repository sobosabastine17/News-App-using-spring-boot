package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.Post;
import com.example.OceanNews.Repo.PostRepo;
import com.example.OceanNews.Serivices.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostImpService implements PostService {
    @Autowired
    PostRepo addPostRepo;
    @Override
    public Post add(Post addPost) {
        return  addPostRepo.save(addPost);
    }

    @Override
    public Iterable<Post> getAll() {
        return addPostRepo.findAll();
    }
    @Override
    public String softDelete(Long id) {
        Post post=addPostRepo.findByPostID(id);
       assert post != null;
            post.setStatus(4L);
            addPostRepo.save(post);
        return "Deleted successfully";
    }


    @Override
    public String hardDelete(Long ID) throws ELException {
        Post post=addPostRepo.findByPostID(ID);
        assert post != null;
        addPostRepo.delete(post);
        return "Deleted successfully";
    }

    @Override
    public List<Post> status(Long postStatus) {
        if (postStatus >= 0 && postStatus <= 3) {
            return addPostRepo.findAllByStatus(postStatus);
        } else {
            // Handle invalid status or return an empty list
            return Collections.emptyList();
        }
    }

    @Override
    public boolean update(Long id, Post postUpdate) {
        Post existingPost= addPostRepo.findByPostID(id);
        assert existingPost != null;
        // check if ID is 0,1,2,3
        if (id >= 0 && id <= 3) {
            existingPost.setStatus(id);
            addPostRepo.save(existingPost);
            return true;
        }
        return false;
    }
    @Override
    public boolean edit(Long id, Post postUpdate) {
        Post existingPost= addPostRepo.findByPostID(id);
        assert existingPost != null;
        existingPost.setTitle(postUpdate.getTitle());
        existingPost.setContent(postUpdate.getContent());
        existingPost.setCreator(postUpdate.getCreator());
        addPostRepo.save(existingPost);
        return false;
    }

    @Override
    public Boolean checkIdExists(Long id) {
        return addPostRepo.existsById(id);
    }

    @Override
    public Post findId(Long id) {
        return addPostRepo.findByPostID(id);
    }

    @Override
    public String restore(Long id) throws ELException {
        Post post=addPostRepo.findByPostID(id);
        assert post != null;
        post.setStatus(1L);
        addPostRepo.save(post);
        return null;
    }

    @Override
    public Post getById(Long id) throws ELException {
        return addPostRepo.findById(id).orElseThrow(()->new ELException("Post with ID:"+id+" could not be found"));
    }

}