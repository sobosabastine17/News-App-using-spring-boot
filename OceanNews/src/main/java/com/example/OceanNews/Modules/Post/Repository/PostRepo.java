package com.example.OceanNews.Modules.Post.Repository;

import com.example.OceanNews.Modules.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    @Query("select p from Post p where p.id = ?1")
    Post findByPostID(Long id);

    @Query("select p from Post p where p.status = ?1")
    List<Post> findAllByStatus(Long status);
    @Query("select count(p) from Post p where p.status = ?1")
    Long countByStatus(Long status);

    @Query("select p from Post p where p.category.id = ?1")
    List<Post> findByCategory_Id(Long id);

    @Query("select (count(p) > 0) from Post p")
    boolean existsByCategory_id(Long id);
}
