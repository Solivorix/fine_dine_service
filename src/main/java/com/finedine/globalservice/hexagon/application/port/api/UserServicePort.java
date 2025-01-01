package com.finedine.globalservice.hexagon.application.port.api;

import com.finedine.globalservice.hexagon.domain.model.UserModel;

public interface UserServicePort {
    UserModel createUser(UserModel userModel);
}
