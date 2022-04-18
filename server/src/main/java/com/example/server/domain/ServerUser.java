package com.example.server.domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "server_user")
public class ServerUser {

    @Id
    @GeneratedValue
    // https://stackoverflow.com/questions/57591764/caused-by-org-h2-jdbc-jdbcsqldataexception-hexadecimal-string-contains-non-hex
    // https://stackoverflow.com/questions/4346898/problems-mapping-uuid-in-jpa-hibernate
    @Type(type="uuid-char")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<ServerPost> posts;
//
//    @ManyToMany
//    private List<Follower> followers;

}
