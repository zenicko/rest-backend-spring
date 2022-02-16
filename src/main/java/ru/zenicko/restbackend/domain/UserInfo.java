package ru.zenicko.restbackend.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserInfo {

    private String userName;
    private Date loginDate;

}
