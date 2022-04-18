package com.example.server;

import com.example.server.domain.ServerPost;
import com.example.server.domain.ServerUser;
import com.example.server.repo.UserRepo;
import com.example.server.types.Follower;
import com.example.server.types.Post;
import com.example.server.types.User;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class UserDatafetcher {

    private final List<User> users = List.of(
            User.newBuilder()
                    .id("123")
                    .name("John Doe")
                    .posts(List.of(
                            Post.newBuilder()
                                    .title("Post #1")
                                    .post_date(LocalDate.now())
                                    .build()
                    ))
//                    .followers(List.of(
//                            Follower.newBuilder()
//                                    .name("Follower #1")
//                                    .build(),
//                            Follower.newBuilder()
//                                    .name("Follower #2")
//                                    .build()
//                            ))
                    .build()

    );

    @Autowired
    private UserRepo userRepo;

    List<Post> toDto(List<ServerPost> entities) {
        return entities.stream()
                .map(e -> Post.newBuilder()
                        .title(e.getTitle())
                        .post_date(e.getDate().atOffset(ZoneOffset.UTC).toLocalDate())
                        .build())
                .collect(Collectors.toList());
    }

    @DgsData(parentType = "Query", field = "user")
    public User getUser(@InputArgument String id, DataFetchingEnvironment dfe) {

        ServerUser byId = userRepo.getById(UUID.fromString(id));
        return User.newBuilder()
                .id(byId.getId().toString())
                .name(byId.getName())
                .posts(toDto(byId.getPosts()))
                .build();

        // https://netflix.github.io/dgs/advanced/context-passing/#pre-loading

//        {
//            if (dfe.getSelectionSet().contains("followers")) {
//                Integer last = (Integer) dfe.getSelectionSet()
//                        .getFields("followers").get(0).getArguments().get("last");
//
//                List<Follower> f = followers;
//                if (last != null) {
//                    f = followers.stream()
//                            .sorted(Comparator.comparing(Follower::getFollow_date))
//                            .limit(last)
//                            .collect(Collectors.toList());
//                }
//
//                User user = users.stream()
//                        .filter(u -> u.getId().equals(id))
//                        .findFirst()
//                        .orElseThrow();
//                user.setFollowers(f);
//                return user;
//
//            }
//            return users.stream()
//                    .filter(u -> u.getId().equals(id))
//                    .findFirst()
//                    .orElseThrow();
//        }
    }

//    @DgsData(parentType = "User", field = "followers")
//    public List<Follower> getFollower(@InputArgument Integer last) {
//        if (last != null) {
//            return followers.stream()
//                    .sorted(Comparator.comparing(Follower::getFollow_date))
//                    .limit(last)
//                    .collect(Collectors.toList());
//        }
//        return followers;
//    }

    private final List<Follower> followers = List.of(
            Follower.newBuilder()
                    .name("Follower #1")
                    .follow_date(LocalDate.now().minusDays(12))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #2")
                    .follow_date(LocalDate.now().minusDays(11))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #3")
                    .follow_date(LocalDate.now().minusDays(10))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #4")
                    .follow_date(LocalDate.now().minusDays(9))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #5")
                    .follow_date(LocalDate.now().minusDays(8))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #6")
                    .follow_date(LocalDate.now().minusDays(7))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #7")
                    .follow_date(LocalDate.now().minusDays(6))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #8")
                    .follow_date(LocalDate.now().minusDays(5))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #9")
                    .follow_date(LocalDate.now().minusDays(4))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #10")
                    .follow_date(LocalDate.now().minusDays(3))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #11")
                    .follow_date(LocalDate.now().minusDays(2))
                    .build(),
            Follower.newBuilder()
                    .name("Follower #12")
                    .follow_date(LocalDate.now().minusDays(1))
                    .build()
    );
}
