package com.mycom.myapp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.user.entity.UserPhone;

public interface UserPhoneRepository extends JpaRepository<UserPhone, Long> {
	// 회원 연락처 추가
	List<UserPhone> findByUserId(Long UserId);
}
