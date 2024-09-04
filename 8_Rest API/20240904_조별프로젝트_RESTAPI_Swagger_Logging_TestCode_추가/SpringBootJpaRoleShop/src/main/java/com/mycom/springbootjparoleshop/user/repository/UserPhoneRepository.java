package com.mycom.springbootjparoleshop.user.repository;

import com.mycom.springbootjparoleshop.user.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPhoneRepository extends JpaRepository<UserPhone, Long> {
    // 회원 연락처 추가
    List<UserPhone> findByUserId(Long UserId);
}
