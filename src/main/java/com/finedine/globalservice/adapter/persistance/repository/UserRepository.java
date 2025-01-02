package com.finedine.globalservice.adapter.persistance.repository;

import com.finedine.globalservice.adapter.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByUserName(String username);
}
