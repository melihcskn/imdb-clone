package com.imdb.user_register.repositories;

import com.imdb.user_register.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    List<User> findAll();
}
