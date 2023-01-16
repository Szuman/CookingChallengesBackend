package com.cookingchallenges.api;

import com.cookingchallenges.domain.user.Grade;
import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.domain.user.dto.EditUser;
import com.cookingchallenges.domain.user.dto.PostUser;
import com.cookingchallenges.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
class UserController {
    private final UserFacade userFacade;

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return userFacade.getUserById(id);
    }

    @GetMapping
    UserDTO getUserByName(@RequestParam String name) {
        return userFacade.getUserByName(name);
    }

    @PostMapping("/register")
    ResponseEntity<Void> postUser(@Valid @RequestBody PostUser postUser) {
        Long UserId = userFacade.postUser(postUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(UserId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> putUser(@Valid @RequestBody EditUser editUser, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userFacade.editUser(editUser, id));
    }

    @PutMapping("/rank/{id}")
    ResponseEntity<UserDTO> putUserRank(@PathVariable Long id, @RequestParam(value = "grade") Grade grade) {
        return ResponseEntity.status(HttpStatus.OK).body(userFacade.changeUsersRank(id, grade));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
    }

}
