package com.cookingchallenges.domain.comment;

import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.comment.dto.EditComment;
import com.cookingchallenges.domain.comment.dto.PostComment;
import com.cookingchallenges.domain.content.ContentFixtures;
import com.cookingchallenges.domain.user.UserFixtures;

import static com.cookingchallenges.domain.user.UserFixtures.DUMMY_USER_ID;

public class CommentFixtures {
    private CommentFixtures() {}

    public static Long DUMMY_COMMENT_ID = 123L;

    public static PostComment postComment() {
        return new PostComment("dummy comment", 1L, 1L);
    }

    public static PostComment postCommentWithContentId(Long contentId) {
        return new PostComment("dummy text", DUMMY_USER_ID, contentId);
    }

    public static Comment comment(Long id) {
        return new Comment(id, "dummy comment", UserFixtures.user(), ContentFixtures.content());
    }

    public static CommentDTO commentDTOWithId(Long id) {
        return new CommentDTO(id, "dummy comment", UserFixtures.userDTO(), ContentFixtures.contentDTOWithId());
    }

    public static CommentDTO commentDTOWithContentId(Long contentId) {
        return new CommentDTO(DUMMY_COMMENT_ID, "dummy comment", UserFixtures.userDTO(), ContentFixtures.contentDTOWithId(contentId));
    }

    public static CommentDTO commentDTOWithUserId(Long userId) {
        return new CommentDTO(DUMMY_COMMENT_ID, "dummy comment", UserFixtures.userDTO(userId), ContentFixtures.contentDTOWithId(1L));
    }

    public static EditComment editComment(String text) {
        return new EditComment(text);
    }
}
