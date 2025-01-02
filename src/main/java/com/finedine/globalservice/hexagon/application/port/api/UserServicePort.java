package com.finedine.globalservice.hexagon.application.port.api;

import com.finedine.globalservice.hexagon.domain.model.UserModel;

import java.util.List;

public interface UserServicePort {
    UserModel createUser(UserModel userModel);

    List<UserModel> getAllUsers();

    boolean deleteUser(String id);

    UserModel getUserById(String id);

    UserModel updateUser(String id, UserModel model);

    UserModel patchUser(String id, UserModel model);
}
