package com.example.server.repo;

import com.example.server.domain.ServerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<ServerUser, UUID> {
}
