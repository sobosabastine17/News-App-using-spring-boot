package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    @Query("select p from Post p where p.postID = ?1")
    Post findByPostID(Long postID);

    List<Post> findBypostStatus(String postStatus);
}
