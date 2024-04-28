package com.example.OceanNews.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class Model implements Serializable {
    @Id
    //@JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
