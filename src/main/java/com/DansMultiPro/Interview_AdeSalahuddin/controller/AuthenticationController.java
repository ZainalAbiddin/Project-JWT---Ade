package com.DansMultiPro.Interview_AdeSalahuddin.controller;

import com.DansMultiPro.Interview_AdeSalahuddin.dto.LoginUserRequest;
import com.DansMultiPro.Interview_AdeSalahuddin.dto.RegistrationDTO;
import com.DansMultiPro.Interview_AdeSalahuddin.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@Tag(name="00. Authentication")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginUserRequest userRequest) throws Exception {
            return userService.authenticationUser(userRequest);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> createUser(@RequestBody RegistrationDTO registrationRequest) {
           return userService.registrationUser(registrationRequest);
    }
}
