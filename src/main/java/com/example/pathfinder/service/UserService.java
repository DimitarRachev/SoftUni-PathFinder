package com.example.pathfinder.service;

import com.example.pathfinder.model.binding.UserLoginBindingModel;
import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.serviceModel.UserServiceModel;

public interface UserService {
  Long registerUser(UserServiceModel map);

  UserServiceModel findUserByUsernameAndPassword(String username, String password);

  boolean loginUser(UserLoginBindingModel userLoginBindingModel);

  void logout();

}
