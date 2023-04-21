package com.cookingchallenges.domain.content;


import com.cookingchallenges.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "enum ('CHALLENGE', 'RECIPE')")
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ElementCollection
    @Column(name = "products")
    private List<String> products;

    @Column
    private String description;

    Content(String title, Type type, User creator, List<String> products, String description) {
        this.title = title;
        this.type = type;
        this.creator = creator;
        this.products = products;
        this.description = description;
    }

}
