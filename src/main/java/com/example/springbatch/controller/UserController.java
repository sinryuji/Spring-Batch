package com.example.springbatch.controller;

import com.example.springbatch.dto.RegisterDto;
import com.example.springbatch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public void register() {
        this.userService.save();
    }
}
