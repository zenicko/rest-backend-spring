package ru.zenicko.restbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.zenicko.restbackend.domain.LoginInfo;
import ru.zenicko.restbackend.domain.UserInfo;
import ru.zenicko.restbackend.exception.InvalidUserNameException;

import java.util.Date;

@RestController
public class BankController {

    @PostMapping("user/login")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if (loginInfo.getUserName().equals("Nick")) {
            return
                    UserInfo.builder()
                            .userName(loginInfo.getUserName())
                            .loginDate(new Date())
                            .build();
        } else {
            throw new InvalidUserNameException();
        }
    }
}
