package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.UserModel;

import java.util.List;

public interface UserDao {
    UserModel createUser(UserModel userModel);

    UserModel findByUserName(String username);

    List<UserModel> getAllUsers();

    boolean deleteUser(String id);

    UserModel getUserById(String id);

    UserModel updateUser(String id, UserModel model);

    UserModel patchUser(String id, UserModel model);
}
