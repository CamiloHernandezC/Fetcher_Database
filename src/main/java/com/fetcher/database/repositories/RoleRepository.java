package com.fetcher.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fetcher.database.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
