package org.example.expense_tracker.repository;

import org.example.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u where u.username = :username")
    List<User> findByUsername(@Param("username") String username);
}
