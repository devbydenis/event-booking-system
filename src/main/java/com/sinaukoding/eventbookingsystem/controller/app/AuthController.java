package com.sinaukoding.eventbookingsystem.controller.app;

import com.sinaukoding.eventbookingsystem.config.UserLoggedInConfig;
import com.sinaukoding.eventbookingsystem.model.request.LoginRequestRecord;
import com.sinaukoding.eventbookingsystem.model.request.RegisterRequestRecord;
import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import com.sinaukoding.eventbookingsystem.service.app.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public BaseResponse<?> register(@RequestBody RegisterRequestRecord request){
        return BaseResponse.ok("Register berhasil", authService.register(request));
    }

    @PostMapping("login")
    public BaseResponse<?> login(@RequestBody LoginRequestRecord request) {
        return BaseResponse.ok("Login berhasil", authService.login(request));
    }

    @GetMapping("logout")
    public BaseResponse<?> logout(@AuthenticationPrincipal UserLoggedInConfig userLoggedInConfig) {
        var userLoggedIn = userLoggedInConfig.getUser();
        authService.logout(userLoggedIn);
        return BaseResponse.ok("Berhasil logout", null);
    }

}
