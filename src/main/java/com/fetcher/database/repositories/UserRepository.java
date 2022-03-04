package com.fetcher.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fetcher.database.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{
}
