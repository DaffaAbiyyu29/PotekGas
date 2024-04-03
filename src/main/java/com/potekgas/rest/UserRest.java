package com.potekgas.rest;

import com.potekgas.model.User;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRest {

    @Autowired
    private UserService userService;

    public UserRest(UserService userService) { this.userService = userService; }

    @GetMapping("/getUsers")
    public DtoResponse getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserActive")
    public DtoResponse getUserActive() {
        return userService.getUserActive();
    }

    @GetMapping("/getUser/{id}")
    public DtoResponse getUserById(@PathVariable int id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/countAdmin")
    public DtoResponse countAdmin() {
        return userService.countAdmin();
    }

    @GetMapping("/countKasir")
    public DtoResponse countKasir() {
        return userService.countKasir();
    }

    @PostMapping("/saveUser")
    public DtoResponse saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/updateUser")
    public DtoResponse updateUser(@RequestBody User user) { return userService.updateUser(user); }

    @PostMapping("/deleteUser")
    public DtoResponse deleteUser(@RequestBody User user) { return userService.deleteUser(user); }

    @PostMapping("/login")
    public DtoResponse LoginUser(@RequestBody User user) {return userService.loginUser(user); }

}
