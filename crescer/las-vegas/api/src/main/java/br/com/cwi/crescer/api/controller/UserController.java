package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.RegisteredCoursesResponse;
import br.com.cwi.crescer.api.controller.response.UserCompleteResponse;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class UserController {

    @Autowired
    private GetRegisteredCoursesService getRegisteredCoursesService;

    @Autowired
    private GetUserAuthenticated getUserAuthenticated;

    @Autowired
    private GetDetailedUserService getDetailedUserService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/registeredCourses")
    public RegisteredCoursesResponse getRegisteredCourses() {
        return getRegisteredCoursesService.getCourses();
        }

    @GetMapping
    public UserResponse getUserAuthenticated(){
        return getUserAuthenticated.get() ;
    }

    @GetMapping("/detail/{userId}")
    public UserCompleteResponse getDetailedUser(@PathVariable Long userId){
        return getDetailedUserService.getDetailedUser(userId);
    }
}