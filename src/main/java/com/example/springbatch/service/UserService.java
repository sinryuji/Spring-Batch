package com.example.springbatch.service;

import com.example.springbatch.dto.RegisterDto;
import com.example.springbatch.entity.User;
import com.example.springbatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(RegisterDto registerDto) {
        User user = new User(registerDto);
        this.userRepository.save(user);
    }
}
