package com.blog_spring_boot_api.blog_spring_boot_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_spring_boot_api.blog_spring_boot_api.model.Rol;

public interface RolRepository  extends JpaRepository<Rol, Long>{

    public Optional<Rol> findByName(String name);
}
