package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    Comment(String text, User creator, Content content) {
        this.text = text;
        this.creator = creator;
        this.content = content;
    }

}
