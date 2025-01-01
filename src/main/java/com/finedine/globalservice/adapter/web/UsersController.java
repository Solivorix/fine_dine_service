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
}
