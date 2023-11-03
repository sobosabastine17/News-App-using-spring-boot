package com.example.OceanNews.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postID;
    @Column
    private String postTitle;
    @Column
    private Long categoryID;
    @Column
    private Long subCategoryID;
    @Column
    private String postContent;
    @Column
    private LocalDate postDate;
    @Column
    private Long postClick;
    @Column
    private Long postShared;
    @Column
    private String creator;
    @Column
    private String postURL;
    @Column
    private String postStatus;

    public Post() {
        // Default constructor
    }
}
