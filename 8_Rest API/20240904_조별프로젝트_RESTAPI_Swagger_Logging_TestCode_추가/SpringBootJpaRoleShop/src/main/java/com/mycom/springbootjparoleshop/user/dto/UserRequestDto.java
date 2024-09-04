package com.mycom.springbootjparoleshop.user.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;

    private String name;

    private String password;

    private String addr1;

    private String addr2;

    private String zipCode;

    private String phone;
}
