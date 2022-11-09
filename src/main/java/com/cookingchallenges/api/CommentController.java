package com.cookingchallenges.api;

import com.cookingchallenges.domain.comment.Comment;
import com.cookingchallenges.domain.comment.CommentDAO;
import com.cookingchallenges.domain.comment.CommentFacade;
import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.comment.dto.EditComment;
import com.cookingchallenges.domain.comment.dto.PostComment;
import com.cookingchallenges.domain.content.Content;
import com.cookingchallenges.domain.content.ContentDAO;
import com.cookingchallenges.domain.content.ContentFacade;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @GetMapping("/{id}")
    CommentDTO getCommentById(@PathVariable Long id) {
        return commentFacade.findById(id);
    }

    @PostMapping
    ResponseEntity<Void> postComment(@Valid @RequestBody PostComment postComment) {
        Long commentId = commentFacade.postComment(postComment);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(commentId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CommentDTO> putComment(@Valid @RequestBody EditComment editComment, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentFacade.editComment(editComment, id));
    }

    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Long id) {
        commentFacade.deleteComment(id);
    }

}
