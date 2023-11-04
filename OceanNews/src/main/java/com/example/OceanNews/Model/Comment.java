package com.example.OceanNews.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Entity
@Setter
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String commentTitle,commentContent,commentDate,user,status;
    private Long postId;

    public Comment() {
        // Default constructor

    }
}
