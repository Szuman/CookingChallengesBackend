package com.cookingchallenges.domain.user;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
//@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=30)
    private String name;

    @Column(nullable = false, length=50, unique = true)
    private String email;

//    @Column(nullable = false, length=256, unique = true)
//    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column
    private String about;

    // TODO
//    @Column
//    private boolean locked;

    User(String name, String email, String about) {
        this.name = name;
        this.email = email;
        this.rank = Rank.BEGINNER;
        this.about = about;
    }

}
