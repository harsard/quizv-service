package com.synsyt.api.quizz.dto;

import com.synsyt.api.quizz.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
