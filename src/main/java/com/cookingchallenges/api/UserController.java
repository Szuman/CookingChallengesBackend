package com.cookingchallenges.api;

import com.cookingchallenges.domain.user.UserFacade;
import com.cookingchallenges.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    //------------------------------------------------------------------------------------------

//    @PostMapping
//    ResponseEntity<Void> makeBooking(@Valid @RequestBody MakeBooking makeBooking) {
//        Long bookingId = userFacade.makeBooking(makeBooking);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(bookingId).toUri();
//        return ResponseEntity.created(location).build();
//    }

}
