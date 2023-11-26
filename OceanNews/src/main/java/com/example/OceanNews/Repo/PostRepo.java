package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    @Query("select p from Post p where p.id = ?1")
    Post findByPostID(Long id);

    @Query("select p from Post p where p.status = ?1")
    List<Post> findAllByStatus(Long status);
}
