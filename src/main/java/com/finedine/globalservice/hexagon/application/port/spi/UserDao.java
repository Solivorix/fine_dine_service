package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.UserModel;

public interface UserDao {
    UserModel createUser(UserModel userModel);
}
