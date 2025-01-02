package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.UserServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.UserDao;
import com.finedine.globalservice.hexagon.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserServicePort {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private final UserDao userDao;
    @Override
    public UserModel createUser(UserModel userModel) {
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(userModel.getPassword(), salt);
        userModel.setPassword(hashedPassword);
        userModel.setPasswordSalt(Base64.getEncoder().encodeToString(salt));
        return userDao.createUser(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    @Override
    public UserModel getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public UserModel updateUser(String id, UserModel model) {
        return userDao.updateUser(id,model);
    }

    @Override
    public UserModel patchUser(String id, UserModel model) {
        return userDao.patchUser(id,model);
    }

    public boolean validatePassword(String username, String rawPassword) {
        UserModel userModel = userDao.findByUserName(username);
        if(userModel != null) {
            byte[] salt = Base64.getDecoder().decode(userModel.getPasswordSalt());
            String hashedPassword = hashPassword(rawPassword, salt);
            return hashedPassword.equals(userModel.getPassword());
        }
        return false;
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private String hashPassword(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt,
                    ITERATIONS,
                    KEY_LENGTH
            );
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Error hashing password", e);
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
