package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.mapper.RestaurantEntityMapper;
import com.finedine.globalservice.adapter.persistance.mapper.UserEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.UserRepository;
import com.finedine.globalservice.hexagon.application.port.spi.UserDao;
import com.finedine.globalservice.hexagon.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDaoAdapter implements UserDao {

    private final UserRepository userRepository;

    @Override
    public UserModel createUser(UserModel userModel) {
        return Optional.ofNullable(userModel)
                .map(UserEntityMapper.INSTANCE::toUserEntity)
                .map(userRepository::save)
                .map(UserEntityMapper.INSTANCE::toUserModel)
                .orElseThrow(() -> new IllegalArgumentException("UserModel cannot be null"));
    }
}
