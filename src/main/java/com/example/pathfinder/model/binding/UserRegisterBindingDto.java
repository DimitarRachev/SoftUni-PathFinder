package com.example.pathfinder.model.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterBindingDto {

    @Size(min = 5, max = 20)
    private String username;

    @Size(min = 5, max = 20)
    private String fullname;

    @Email
    private String email;

    @Min(10)
    @Max(90)
    private int age;

    @Size(min = 5, max =20)
    private String password;

    @Size(min = 5, max =20)
    private String confirmPassword;
}
