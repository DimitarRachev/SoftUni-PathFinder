package com.example.pathfinder.model.serviceModel;

import com.example.pathfinder.model.entity.RoleEntity;
import com.example.pathfinder.model.entity.enumeration.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceModel {

    private Long Id;

    private int age;

    private String fullname;

    private LevelEnum level;

    private String email;

    private String password;

    private String username;

    private Set<RoleEntity> roles;
}
