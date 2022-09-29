package com.amsidh.mvc.service;

import com.amsidh.mvc.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(Integer id);

    UserInfo updateUserInfo(Integer id, UserInfo userInfo);

    List<UserInfo> getUsers();

    void deleteUserInfo(Integer id);

}
