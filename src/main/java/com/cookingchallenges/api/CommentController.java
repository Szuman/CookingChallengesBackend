package com.cookingchallenges.api;

import com.cookingchallenges.domain.comment.CommentFacade;
import com.cookingchallenges.domain.comment.dto.CommentDTO;
import com.cookingchallenges.domain.comment.dto.EditComment;
import com.cookingchallenges.domain.comment.dto.PostComment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "Get comment by content", description = "Get comment by contents id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/content/{id}")
    List<CommentDTO> getCommentsByContent(@PathVariable Long id) {
        return commentFacade.findByContentId(id);
    }

    @Operation(summary = "Get comment by user", description = "Get comment by users id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/user/{id}")
    List<CommentDTO> getCommentsByUser(@PathVariable Long id) {
        return commentFacade.findByUserId(id);
    }

    @Operation(summary = "Get comment by id", description = "Get comment by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    CommentDTO getCommentById(@PathVariable Long id) {
        return commentFacade.findById(id);
    }

    @Operation(summary = "Post comment", description = "Post new comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    ResponseEntity<Void> postComment(@Valid @RequestBody PostComment postComment) {
        Long commentId = commentFacade.postComment(postComment);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(commentId).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edit comment", description = "Edit comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    ResponseEntity<CommentDTO> putComment(@Valid @RequestBody EditComment editComment, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentFacade.editComment(editComment, id));
    }

    @Operation(summary = "Delete comment", description = "Delete comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Long id) {
        commentFacade.deleteComment(id);
    }

}
