package com.example.server.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "post")
public class ServerPost {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    private String title;

    private Instant date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ServerUser user;
}
