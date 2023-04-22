package com.cookingchallenges.domain.content;

import com.cookingchallenges.domain.content.dto.ContentDTO;
import com.cookingchallenges.domain.content.dto.EditContent;
import com.cookingchallenges.domain.content.dto.PostContent;
import com.cookingchallenges.domain.user.UserFixtures;

import java.util.List;

import static com.cookingchallenges.domain.user.UserFixtures.DUMMY_USER_ID;

public class ContentFixtures {
    private ContentFixtures() {}

    public static Long DUMMY_CONTENT_ID = 456L;

    public static ContentDTO contentDTOWithId() {
        return new ContentDTO(DUMMY_CONTENT_ID, "Pancakes", "RECIPE", UserFixtures.userDTO(), "simple recipe", List.of("flour", "milk", "egg"));
    }

    public static ContentDTO contentDTOWithId(Long id) {
        return new ContentDTO(id, "Pancakes", "RECIPE", UserFixtures.userDTO(), "simple recipe", List.of("flour", "milk", "egg"));
    }

    public static ContentDTO contentDTOWithTitle(String title) {
        return new ContentDTO(DUMMY_CONTENT_ID, title, "RECIPE", UserFixtures.userDTO(), "simple recipe", List.of("flour", "milk", "egg"));
    }

    public static ContentDTO contentDTOWithUserId(Long userId) {
        return new ContentDTO(DUMMY_CONTENT_ID, "Pancakes", "RECIPE", UserFixtures.userDTO(userId), "simple recipe", List.of("flour", "milk", "egg"));
    }

    public static Content content() {
        return new Content(DUMMY_CONTENT_ID, "Pancakes", Type.RECIPE, UserFixtures.user(), List.of("flour", "milk", "egg"), "simple recipe");
    }

    public static Content contentWithId(Long id) {
        return new Content(id, "Pancakes", Type.RECIPE, UserFixtures.user(), List.of("flour", "milk", "egg"), "simple recipe");
    }

    public static Content contentWithTitle(String title) {
        return new Content(DUMMY_CONTENT_ID, title, Type.RECIPE, UserFixtures.user(), List.of("flour", "milk", "egg"), "simple recipe");
    }

    public static Content contentWithUserId(Long userId) {
        return new Content(DUMMY_CONTENT_ID, "Pancakes", Type.RECIPE, UserFixtures.user(userId), List.of("flour", "milk", "egg"), "simple recipe");
    }

    public static Content noIdContentWithUserId(Long userId) {
        return new Content("Pancakes", Type.RECIPE, UserFixtures.user(userId), List.of("flour", "milk", "egg"), "simple recipe");
    }

    public static PostContent postContent() {
        return new PostContent("Pancakes", "RECIPE", DUMMY_USER_ID, List.of("flour", "milk", "egg"), "simple recipe");
    }

    public static PostContent postContentWithUserId(Long userId) {
        return new PostContent("Pancakes", "RECIPE", userId, List.of("flour", "milk", "egg"), "simple recipe");
    }

    public static EditContent editContentWithTitle(String title) {
        return new EditContent(title, List.of("flour", "milk", "egg"), "simple recipe");
    }
}
