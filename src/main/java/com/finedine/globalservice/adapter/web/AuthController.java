package com.finedine.globalservice.adapter.web;

import com.finedine.api.AuthApi;
import com.finedine.globalservice.adapter.web.mapper.UserDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.UserServicePort;
import com.finedine.model.ForgotPasswordRequestDto;
import com.finedine.model.LoginRequestDto;
import com.finedine.model.LoginResponseDto;
import com.finedine.model.ResetPasswordRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApi {

    private final UserServicePort userServicePort;

    @Override
    public ResponseEntity<Void> forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponseDto> loginUser(LoginRequestDto loginRequestDto) {
        boolean isValidCred = userServicePort.loginUser(UserDtoMapper.INSTANCE.toUserModel(loginRequestDto));
        if (!isValidCred) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto());
        }
        // Generate JWT token upon successful login
        String token = "Login successful";
        LoginResponseDto response = new LoginResponseDto().token(token);
        return ResponseEntity.ok(new LoginResponseDto().token(token));
    }

    @Override
    public ResponseEntity<Void> resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
        return null;
    }
}
