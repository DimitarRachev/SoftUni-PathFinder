package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.UserLoginBindingModel;
import com.example.pathfinder.model.binding.UserRegisterBindingDto;
import com.example.pathfinder.model.serviceModel.UserServiceModel;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @ModelAttribute
    public UserRegisterBindingDto userRegisterBindingDto() {
        UserRegisterBindingDto dto = new UserRegisterBindingDto();
//        add prepopulated field values
//        dto.setFullName("FullName");
//        dto.setAge(666);
//        dto.setUsername("username");
//        dto.setEmail("some@email.com");
        return dto;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("isNotExist", false);
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingDto userRegisterBindingDto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingDto.getPassword().equals(userRegisterBindingDto.getConfirmPassword())) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingDto", userRegisterBindingDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingDto", bindingResult);

            return "redirect:register";
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingDto, UserServiceModel.class);
        Long id = userService.registerUser(userServiceModel);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        UserServiceModel user = userService.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
        if (user == null) {
            redirectAttributes
                    .addFlashAttribute("isNotExist", true)
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        userService.loginUser(userLoginBindingModel);

        return "redirect:/";
    }

    @GetMapping("/logout")
        public String logout() {
        userService.logout();
        return "redirect:/";
    }
}

