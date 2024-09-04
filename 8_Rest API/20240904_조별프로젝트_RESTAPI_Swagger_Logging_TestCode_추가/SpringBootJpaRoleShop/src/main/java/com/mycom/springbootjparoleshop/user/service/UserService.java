package com.mycom.springbootjparoleshop.user.service;

import com.mycom.springbootjparoleshop.user.dto.UserRequestDto;
import com.mycom.springbootjparoleshop.user.dto.UserResultDto;
import com.mycom.springbootjparoleshop.user.entity.User;

import java.util.List;

public interface UserService {
    UserResultDto insertUser(UserRequestDto userRequestDto);

    UserResultDto login(String email, String password);

    UserResultDto validateDuplicateEmail(String email);

    // 회원 정보 조회
    UserResultDto detailUser(Long userId);

    // 회원 정보 수정
    UserResultDto updateUser(Long userId, String email, String name, String password);

    // 회원 주소 조회
    UserResultDto detailAddress(Long id);

    // 회원 주소 등록
    UserResultDto insertAddress(Long id, String zipCode, String addr1, String addr2);

    // 회원 연락처 조회
    UserResultDto detailPhone(Long id);

    // 회원 연락처 등록
    UserResultDto insertPhone(Long id, String phone);

    List<User> listUsers();
}
