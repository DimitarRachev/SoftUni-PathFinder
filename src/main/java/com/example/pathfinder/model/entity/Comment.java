package com.example.pathfinder.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column
    private Boolean approved;
    @Column
    private String textContent;
    @Column
    private LocalDateTime created;

    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private RouteEntity route;

    public Comment() {
    }
}
