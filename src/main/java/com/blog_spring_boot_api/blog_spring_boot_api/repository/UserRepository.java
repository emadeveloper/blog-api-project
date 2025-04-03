package com.blog_spring_boot_api.blog_spring_boot_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_spring_boot_api.blog_spring_boot_api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsernameOrEmail(String username, String email);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
