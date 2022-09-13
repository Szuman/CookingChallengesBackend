package com.cookingchallenges.api;

import com.cookingchallenges.domain.comment.Comment;
import com.cookingchallenges.domain.comment.CommentDAO;
import com.cookingchallenges.domain.comment.CommentFacade;
import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.ContentDAO;
import com.cookingchallenges.domain.content.ContentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentFacade commentFacade;

    @GetMapping("/content/{id}")
    List<CommentDTO> getCommentsByContent(@PathVariable Long id) {
        return commentFacade.findByContentId(id);
    }

    @GetMapping("/user/{id}")
    List<CommentDTO> getCommentsByUser(@PathVariable Long id) {
        return commentFacade.findByUserId(id);
    }

}
