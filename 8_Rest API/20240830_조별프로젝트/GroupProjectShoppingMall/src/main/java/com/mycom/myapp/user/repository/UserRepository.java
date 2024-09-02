package com.mycom.myapp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycom.myapp.user.dto.UserAddressDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserPhoneDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserAddress;

public interface UserRepository extends JpaRepository<User, Long>{

	// 로그인
	User findByEmail(String email);
	
	//Optional<User> findById(Long id);
	
	
	
}
