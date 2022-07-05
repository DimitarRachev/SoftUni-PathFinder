package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enumeration.LevelEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="routes")
public class RouteEntity extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private LevelEnum level;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "video_url", columnDefinition = "TEXT")
    private String videoUrl;

    @ManyToOne
    private UserEntity author;

    @ManyToMany
    private Set<Category> categories;
}
