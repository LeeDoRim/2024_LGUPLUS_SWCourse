package com.mycom.myapp.user.service;

import java.util.List;

import com.mycom.myapp.user.dto.UserAddressDto;
import com.mycom.myapp.user.dto.UserPhoneDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.UserAddress;

public interface UserService {

	// 로그인
	UserResultDto login(String email, String password);
	
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
}
