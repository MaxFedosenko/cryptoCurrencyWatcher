package com.idf.repositories;

import com.idf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserName(String userName);
    List<User> findAll();

}
