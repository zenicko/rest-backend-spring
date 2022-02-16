package ru.zenicko.restbackend.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginInfo {
    private String userName;
    private String password;
}
