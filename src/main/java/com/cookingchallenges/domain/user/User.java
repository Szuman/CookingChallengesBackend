package com.cookingchallenges.domain.user;

import lombok.*;

import javax.persistence.*;

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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column
    private String about;

    User(String name, String email, String about) {
        this.name = name;
        this.email = email;
        this.rank = Rank.BEGINNER;
        this.about = about;
    }
}
