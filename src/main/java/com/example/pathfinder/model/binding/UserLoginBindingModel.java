package com.example.pathfinder.model.binding;

import lombok.*;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginBindingModel {

    @Size(min = 4, max = 20)
    private String username;

    @Size(min = 4, max = 20)
    private String password;

}
