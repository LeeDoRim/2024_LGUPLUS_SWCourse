package com.mycom.springbootjparoleshop.user.repository;

import com.mycom.springbootjparoleshop.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    // 회원의 주소 목록
    List<UserAddress> findByUserId(Long UserId);
}
