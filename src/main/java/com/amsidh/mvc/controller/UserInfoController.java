package com.amsidh.mvc.controller;

import com.amsidh.mvc.model.UserInfo;
import com.amsidh.mvc.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/users")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/{id}")
    public UserInfo getUserInfo(@PathVariable("id") Integer id) {
        return userInfoService.getUserInfo(id);
    }

    @GetMapping
    public List<UserInfo> getAllUsers() {
        return userInfoService.getUsers();
    }

    @PostMapping
    public UserInfo saveUser(@RequestBody UserInfo userInfo) {
        return userInfoService.saveUserInfo(userInfo);
    }

    @PutMapping("/{id}")
    public UserInfo updateUser(@PathVariable("id") Integer id, @RequestBody UserInfo userInfo) {
        return userInfoService.updateUserInfo(id, userInfo);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userInfoService.deleteUserInfo(id);
        return "User with user id " + id + " is deleted successfully";
    }

}
