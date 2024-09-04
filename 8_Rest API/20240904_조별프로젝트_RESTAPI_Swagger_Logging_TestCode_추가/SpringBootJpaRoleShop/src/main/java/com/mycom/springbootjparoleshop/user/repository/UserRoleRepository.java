package com.mycom.springbootjparoleshop.user.repository;

import com.mycom.springbootjparoleshop.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
