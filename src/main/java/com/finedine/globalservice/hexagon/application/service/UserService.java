package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.UserServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.UserDao;
import com.finedine.globalservice.hexagon.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserServicePort {
    private final UserDao userDao;
    @Override
    public UserModel createUser(UserModel userModel) {
        return userDao.createUser(userModel);
    }
}
