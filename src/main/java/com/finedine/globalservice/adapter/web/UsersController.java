package com.finedine.globalservice.adapter.web;

import com.finedine.api.UsersApi;
import com.finedine.globalservice.adapter.web.mapper.RestaurantDtoMapper;
import com.finedine.globalservice.adapter.web.mapper.UserDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.UserServicePort;
import com.finedine.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UsersController implements UsersApi {

    private final UserServicePort userServicePort;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(UserDtoMapper.INSTANCE::toUserModel)
                .map(userServicePort::createUser)
                .map(UserDtoMapper.INSTANCE::toUserDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("UserDto cannot be null"));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return userServicePort.deleteUser(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(
                Optional.ofNullable(userServicePort.getAllUsers())
                        .map(UserDtoMapper.INSTANCE::toUserDtoList)
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        return Optional.ofNullable(userServicePort.getUserById(id))
                .map(UserDtoMapper.INSTANCE::toUserDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserDto> patchUser(String id, UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(UserDtoMapper.INSTANCE::toUserModel)
                .map(model -> userServicePort.patchUser(id, model))
                .map(UserDtoMapper.INSTANCE::toUserDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("UserDto cannot be null"));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(String id, UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(UserDtoMapper.INSTANCE::toUserModel)
                .map(model -> userServicePort.updateUser(id, model))
                .map(UserDtoMapper.INSTANCE::toUserDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("UserDto cannot be null"));
    }
}
