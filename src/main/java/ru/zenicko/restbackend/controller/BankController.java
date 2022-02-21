package ru.zenicko.restbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zenicko.restbackend.RestBackendApplication;
import ru.zenicko.restbackend.domain.LoginInfo;
import ru.zenicko.restbackend.domain.UserInfo;
import ru.zenicko.restbackend.exception.InvalidUserNameException;
import ru.zenicko.restbackend.exception.UserExistException;

import java.util.List;

@RestController
public class BankController {

    @PostMapping("user/login")
    @ApiOperation("Get the user info")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if (RestBackendApplication.dataBase.isExistUser(loginInfo.getUserName())) {
            return RestBackendApplication.dataBase.getUserInfoLastLogin(loginInfo.getUserName());
        } else {
            throw new InvalidUserNameException();
        }
    }

    @GetMapping("/users")
    @ApiOperation("Get the info about all users")
    public List<UserInfo> getAllUsers() {
        return RestBackendApplication.dataBase.getAllUserInfo();
    }

    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation("Add a new user")
    public UserInfo addNewUser(@RequestBody LoginInfo loginInfo) {
        if (!RestBackendApplication.dataBase.isExistUser(loginInfo.getUserName())) {
            RestBackendApplication.dataBase.addRow(loginInfo);
            return RestBackendApplication.dataBase.getUserInfoLastLogin(loginInfo.getUserName());
        } else {
            throw new UserExistException();
        }
    }

}
