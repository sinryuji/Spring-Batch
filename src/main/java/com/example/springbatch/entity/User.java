package com.example.springbatch.entity;

import com.example.springbatch.dto.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user")
public class User {

    public User(RegisterDto registerDto) {
        this.id = registerDto.getId();
        this.password = registerDto.getPassword();;
        this.name = registerDto.getName();;
    }

    private String id;
    private String password;
    private String name;
    private long salary;
    private int bonus;

}
