package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.entity.UserEntity;
import com.finedine.globalservice.adapter.persistance.mapper.UserEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.UserRepository;
import com.finedine.globalservice.hexagon.application.port.spi.UserDao;
import com.finedine.globalservice.hexagon.domain.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    @Override
    public UserModel findByUserName(String username) {
        return userRepository.findByUserName(username)
                .map(UserEntityMapper.INSTANCE::toUserModel)
                .orElse(null);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserEntityMapper.INSTANCE::toUserModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserModel getUserById(String id) {
        return userRepository.findById(id)
                .map(UserEntityMapper.INSTANCE::toUserModel)
                .orElse(null);
    }

    @Override
    public UserModel updateUser(String id, UserModel model) {
        return userRepository.findById(id)
                .map(existingEntity -> {
                    model.setUserId(existingEntity.getUserId());
                    UserEntity entity = userRepository.save(UserEntityMapper.INSTANCE.toUserEntity(model));
                    return UserEntityMapper.INSTANCE.toUserModel(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public UserModel patchUser(String id, UserModel model) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Update only the fields that are not null in the DTO
        Optional.ofNullable(model.getUserName()).ifPresent(existingUser::setUserName);
        Optional.ofNullable(model.getRestaurantId()).ifPresent(existingUser::setRestaurantId);
        Optional.ofNullable(model.getPassword()).ifPresent(existingUser::setPassword);
        Optional.ofNullable(model.getContactNumber()).ifPresent(existingUser::setContactNumber);
        Optional.ofNullable(model.getEmail()).ifPresent(existingUser::setEmail);
        Optional.ofNullable(model.getRole()).ifPresent(existingUser::setRole);
        Optional.ofNullable(model.getUpdatedBy()).ifPresent(existingUser::setUpdatedBy);

        existingUser = userRepository.save(existingUser);
        return UserEntityMapper.INSTANCE.toUserModel(existingUser);
    }
}
