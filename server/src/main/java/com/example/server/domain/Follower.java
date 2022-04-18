package com.example.server.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
//@Entity
public class Follower {

    private UUID id;

    private String name;

    private Instant followDate;

    private List<ServerUser> users;
}
