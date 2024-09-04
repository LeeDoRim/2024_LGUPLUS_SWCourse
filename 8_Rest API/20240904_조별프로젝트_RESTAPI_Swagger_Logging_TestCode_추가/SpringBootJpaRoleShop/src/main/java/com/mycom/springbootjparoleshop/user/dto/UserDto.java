package com.mycom.springbootjparoleshop.user.dto;

import com.mycom.springbootjparoleshop.orders.entity.Orders;
import lombok.Data;

import java.util.*;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String name;

    private String password;

    private List<UserAddressDto> addresses = new ArrayList<>();

    private List<UserPhoneDto> phones = new ArrayList<>();

    private Map<Long, String> roles = new HashMap<>();

    private List<Orders> orders;
}
