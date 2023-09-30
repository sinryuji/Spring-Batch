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

    public void save() {
        for (int i = 1; i < 101; ++i) {
            User user = User.builder()
                .id("test" + i)
                .name("test" + i)
                .password("test")
                .salary(1000 * i)
                .bonus(10 * i)
                .build();
            this.userRepository.save(user);
        }
    }
}
