package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.binding.UserLoginBindingModel;
import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.entity.enumeration.LevelEnum;
import com.example.pathfinder.model.serviceModel.UserServiceModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    @Override
    public Long registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setLevel(LevelEnum.BEGINNER);
        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {

        UserEntity user = userRepository.findByUsernameAndPassword(username, password);
        return user != null ? modelMapper.map(user, UserServiceModel.class) : null;
    }

    @Override
    public boolean loginUser(UserLoginBindingModel userLoginBindingModel) {
       UserServiceModel userServiceModel =findUserByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
      currentUser.setId(userServiceModel.getId());
      currentUser.setUsername(userServiceModel.getUsername());
      //add others fields if needed
        return true;
    }

    @Override
    public void logout() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }
}
