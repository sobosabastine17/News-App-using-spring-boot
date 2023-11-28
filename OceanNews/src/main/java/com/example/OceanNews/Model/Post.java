package com.example.OceanNews.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;
    @Column
    private String title;
    @Column
    private Long categoryID;
    @Column
    private Long subCategoryID;
    @Column
    private String content;
    @Column
    private LocalDate date=LocalDate.now();
    @Column
    private Long click=0L;
    @Column
    private Long shared=0L;
    @Column
    private String creator;
    @Column
    private String url;
    @Column
    private Long status=0L;

    public Post() {
        // Default constructor
    }
}
