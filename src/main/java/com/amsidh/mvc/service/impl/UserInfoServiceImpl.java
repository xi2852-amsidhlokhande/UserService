package com.amsidh.mvc.service.impl;

import com.amsidh.mvc.entity.UserInfoEntity;
import com.amsidh.mvc.model.UserInfo;
import com.amsidh.mvc.repository.UserInfoRepository;
import com.amsidh.mvc.service.UserInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        log.info("Save UserInfo method called");
        if (userInfo.getRow_update_time() == null) {
            userInfo.setRow_update_time(new Date());
        }
        UserInfoEntity userInfoEntity = objectMapper.convertValue(userInfo, UserInfoEntity.class);
        UserInfoEntity savedUserInfoEntity = userInfoRepository.save(userInfoEntity);
        log.info("UserInfo saved successfully : {}", savedUserInfoEntity);
        return objectMapper.convertValue(savedUserInfoEntity, UserInfo.class);
    }

    @Override
    public UserInfo getUserInfo(Integer id) {
        log.info("Get UserInfo with id {}", id);
        return objectMapper.convertValue(userInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id)), UserInfo.class);
    }

    @Override
    public UserInfo updateUserInfo(Integer id, UserInfo userInfo) {
        log.info("Updating UserInfo with id {}  and userinfo {}", id, userInfo);
        UserInfoEntity updatedUserInfoEntity = userInfoRepository.findById(id).map(userInfoEntity -> {
            Optional.ofNullable(userInfo.getFirstName()).ifPresent(userInfoEntity::setFirstName);
            Optional.ofNullable(userInfo.getLastName()).ifPresent(userInfoEntity::setLastName);
            Optional.ofNullable(userInfo.getCity()).ifPresent(userInfoEntity::setCity);
            Optional.ofNullable(userInfo.getAddress()).ifPresent(userInfoEntity::setAddress);
            Optional.ofNullable(userInfo.getData()).ifPresent(userInfoEntity::setData);
            if (userInfo.getRow_update_time() != null) {
                userInfoEntity.setRow_update_time(userInfo.getRow_update_time());
            } else {
                userInfoEntity.setRow_update_time(new Date());
            }
            return userInfoRepository.save(userInfoEntity);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
        log.info("UserInfo updated successfully : {}", updatedUserInfoEntity);
        return objectMapper.convertValue(updatedUserInfoEntity, UserInfo.class);
    }

    @Override
    public List<UserInfo> getUsers() {
        log.info("Fetching all the Users");
        return objectMapper.convertValue(userInfoRepository.findAll(), new TypeReference<List<UserInfo>>() {
        });
    }

    @Override
    public void deleteUserInfo(Integer id) {
        log.info("Deleting the user with id {}", id);
        userInfoRepository.deleteById(id);
    }
}
