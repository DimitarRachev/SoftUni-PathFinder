package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.entity.enumeration.LevelEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Set;



@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    private int age;

    @Column(name= "full_name")
    private String fullname;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;

    public UserEntity() {
    }
}
