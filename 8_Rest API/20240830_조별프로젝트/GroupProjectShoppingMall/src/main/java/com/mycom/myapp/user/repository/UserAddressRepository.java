package com.mycom.myapp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.user.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
	// 회원의 주소 목록
	List<UserAddress> findByUserId(Long UserId);
}
