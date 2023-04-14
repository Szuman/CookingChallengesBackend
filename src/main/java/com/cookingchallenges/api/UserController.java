package com.cookingchallenges.api;

import com.cookingchallenges.domain.user.Grade;
import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.domain.user.dto.EditUser;
import com.cookingchallenges.domain.user.dto.UserDTO;
import com.cookingchallenges.domain.user.dto.UserWithPassDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
class UserController {

    private final UserFacade userFacade;

    @Operation(summary = "Get user", description = "Get user by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return userFacade.getUserById(id);
    }

    @Operation(summary = "Get user by name", description = "Get user by name")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    UserDTO getUserByName(@RequestParam String name) {
        return userFacade.getUserByName(name);
    }

    @Operation(summary = "Get user info with password", description = "Only for presentation")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/pass/{id}")
    UserWithPassDTO getUserWithPass(@PathVariable Long id) {
        return userFacade.getUserWithPass(id);
    }

    @Operation(summary = "Edit user", description = "Edit user by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    ResponseEntity<UserDTO> putUser(@Valid @RequestBody EditUser editUser, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userFacade.editUser(editUser, id));
    }

    @Operation(summary = "Update users rank", description = "Change users rank")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/rank/{id}")
    ResponseEntity<UserDTO> putUserRank(@PathVariable Long id, @RequestParam(value = "grade") Grade grade) {
        return ResponseEntity.status(HttpStatus.OK).body(userFacade.changeUsersRank(id, grade));
    }

    @Operation(summary = "Delete user", description = "Delete user by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
    }

}
