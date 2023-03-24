package com.bbeerbear.grpcflix.user.repository;

import com.bbeerbear.grpcflix.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
