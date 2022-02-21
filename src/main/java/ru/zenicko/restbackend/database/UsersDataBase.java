package ru.zenicko.restbackend.database;

import ru.zenicko.restbackend.domain.LoginInfo;
import ru.zenicko.restbackend.domain.UserInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersDataBase {
    private HashMap<String, UserInfo> users = new HashMap<>();

    public UsersDataBase() {
        users.put("Nick", UserInfo.builder()
                        .userName("Nick")
                        .loginDate(new Date(1645098134517l))
                        .build()
        );
        users.put("Dima", UserInfo.builder()
                        .userName("Dima")
                        .loginDate(new Date(1645098144413l))
                        .build()
        );
        users.put("Ivan", UserInfo.builder()
                .userName("Ivan")
                .loginDate(new Date(164509812313l))
                .build());
    }

    public UsersDataBase addRow(LoginInfo loginInfo) {
        users.put(loginInfo.getUserName(),
                UserInfo.builder()
                        .userName(loginInfo.getUserName())
                        .loginDate(new Date())
                        .build());
        return this;
    }


    public List<UserInfo> getAllUserInfo() {
        return users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public boolean isExistUser(String userName) {
        return users.containsKey(userName);
    }

    public UserInfo getUserInfoLastLogin(String userName) {
        if (users.containsKey(userName)) {
           return users.get(userName);
        } else {
            return null;
        }
    }
}
