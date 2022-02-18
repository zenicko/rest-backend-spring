package ru.zenicko.restbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.zenicko.restbackend.RestBackendApplication;
import ru.zenicko.restbackend.domain.LoginInfo;
import ru.zenicko.restbackend.domain.UserInfo;
import ru.zenicko.restbackend.exception.InvalidUserNameException;
import ru.zenicko.restbackend.exception.UserExistException;

import java.util.List;

@RestController
public class BankController {


    @PostMapping("user/login")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if (RestBackendApplication.dataBase.isExistUser(loginInfo.getUserName())) {
            return RestBackendApplication.dataBase.getUserInfoLastLogin(loginInfo.getUserName());
        } else {
            throw new InvalidUserNameException();
        }
    }

    @GetMapping("/users")
    public List<UserInfo> getAllUsers() {
        return RestBackendApplication.dataBase.getAllUserInfo();
    }

    @PostMapping("/user")
    public UserInfo addNewUser(@RequestBody LoginInfo loginInfo) {
        if (!RestBackendApplication.dataBase.isExistUser(loginInfo.getUserName())) {
            RestBackendApplication.dataBase.addRow(loginInfo);
            return RestBackendApplication.dataBase.getUserInfoLastLogin(loginInfo.getUserName());
        } else {
            throw new UserExistException();
        }
    }

}
