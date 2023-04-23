package com.cookingchallenges.api;

import com.cookingchallenges.domain.user.Grade;
import com.cookingchallenges.domain.user.UserApiFacade;
import com.cookingchallenges.domain.user.dto.EditUser;
import com.cookingchallenges.domain.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
class UserController {

    private final UserApiFacade userFacade;

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
