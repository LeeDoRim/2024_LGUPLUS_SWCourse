package com.mycom.springbootjparoleshop.user.repository;

import com.mycom.springbootjparoleshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
