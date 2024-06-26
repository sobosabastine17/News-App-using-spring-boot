package com.example.OceanNews.Modules.Post.ServiceImplementation;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Modules.Category.Category;
import com.example.OceanNews.Modules.Category.Repository.CategoryRepository;
import com.example.OceanNews.Modules.Post.Post;
import com.example.OceanNews.Modules.Post.Repository.PostRepo;
import com.example.OceanNews.Modules.Post.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImplementationService implements PostService {
    @Autowired
    PostRepo addPostRepo;
    @Autowired
    private CategoryRepository categoryRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public Post add(Post addPost) throws ELException {
        // Check if addPost contains a valid Category object with a non-null ID
        if (addPost.getCategory() == null || addPost.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category ID must be provided");
        }

        // Retrieve the Category object based on the provided ID
        Category category = categoryRepository.findById(addPost.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
         addPostRepo.save(addPost);
        return addPost;
    }
    @Override
    public Iterable<Post> getAll() {
        return addPostRepo.findAll();
    }

    @Override
    public void softDelete(Long id) {
        Post post = addPostRepo.findByPostID(id);
        assert post != null;
        post.setStatus(6L);
        addPostRepo.save(post);
    }


    @Override
    public void hardDelete(Long ID) throws ELException {
        Post post = addPostRepo.findByPostID(ID);
        assert post != null;
        addPostRepo.delete(post);
    }

    @Override
    public List<Post> status(Long postStatus) {
        if (postStatus >= 0 && postStatus <= 6) {
            // Handle empty list
            if (addPostRepo.countByStatus(postStatus) == 0) {
                throw new ELException("Status ID: " + postStatus + " is empty");
            }
            return addPostRepo.findAllByStatus(postStatus);
        } else if (addPostRepo.findByPostID(postStatus) == null) {
            // Handle empty list
            throw new ELException("Status ID: " + postStatus + " does not exist");
        } else {
            // Handle invalid status or return an empty list
            throw new ELException("Invalid status ID");
        }
    }

    @Override
    public boolean update(Long id, Post postUpdate) {
        Post existingPost = addPostRepo.findByPostID(id);
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
    public void edit(Long id, Post postUpdate) {
        Post existingPost = addPostRepo.findByPostID(id);
        assert existingPost != null;
        existingPost.setTitle(postUpdate.getTitle());
        existingPost.setContent(postUpdate.getContent());
        existingPost.setCreator(postUpdate.getCreator());
        addPostRepo.save(existingPost);
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
        Post post = addPostRepo.findByPostID(id);
        assert post != null;
        post.setStatus(3L);
        addPostRepo.save(post);
        return null;
    }

    @Override
    public Post getById(Long id) throws ELException {
        return addPostRepo.findById(id).orElseThrow(() -> new ELException("Post with ID:" + id + " could not be found"));
    }

    @Override
    public Iterable<Post> getByCategory(Long categoryId) throws ELException {
        return addPostRepo.findByCategory_Id(categoryId);
    }

    @Override
    public boolean categoryIdExists(Long id) throws ELException {
        //check if category id exists
        return categoryRepository.existsById(id);
    }
}