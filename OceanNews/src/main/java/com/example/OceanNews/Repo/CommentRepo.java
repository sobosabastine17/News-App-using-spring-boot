package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query("select count(c) from Comment c where c.status = ?1")
    long countByStatus(Long status);
    @Query("select c from Comment c where c.status = ?1")
    List<Comment> findAllByStatus(Long status);

    List<Comment> findAllByPostId(Long id);

    @Query("select count(c) from Comment c where c.postId = ?1")
    Long countByPostId(Long id);
}
