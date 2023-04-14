package com.cookingchallenges.api;

import com.cookingchallenges.domain.user.User;
import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.domain.user.dto.LoginRequest;
import com.cookingchallenges.domain.user.dto.PostUser;
import com.cookingchallenges.domain.user.dto.SignupResponse;
import com.cookingchallenges.security.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
class AuthorizationController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserFacade userFacade;

    @Operation(summary = "Register", description = "Register new user")
    @PostMapping("/register")
    ResponseEntity<Void> postUser(@Valid @RequestBody PostUser postUser) {
        Long UserId = userFacade.postUser(postUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(UserId).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Register", description = "Register new user")
    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authenticationToken = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        User user = (User) authenticationToken.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(user);
        return ResponseEntity.ok(new SignupResponse(jwt, user.getId(),
                user.getUsername(), user.getEmail(), user.getGrade()));
    }

}
