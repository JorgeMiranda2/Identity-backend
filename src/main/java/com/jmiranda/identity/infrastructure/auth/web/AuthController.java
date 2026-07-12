package com.jmiranda.identity.infrastructure.auth.web;

import com.jmiranda.identity.application.auth.SignUp;
import com.jmiranda.identity.application.auth.SignUpCommand;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.infrastructure.user.web.dto.request.SignUpRequest;
import com.jmiranda.identity.infrastructure.user.web.dto.response.UserIdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/register")
public class AuthController {
    private final SignUp signUp;

    public AuthController(SignUp signUp){
        this.signUp = signUp;
    }

    @PostMapping
    public ResponseEntity<UserIdResponse> signUp(@Valid @RequestBody SignUpRequest request){
        SignUpCommand signUpCommand = new SignUpCommand(
                request.firstName(),
                request.lastName(),
                request.personalEmail(),
                request.institutionalEmail(),
                request.phoneNumber(),
                request.birthDate(),
                request.identificationTypeId(),
                request.identificationCode(),
                request.username(),
                request.password()
        );

        UserId userIdCreated = signUp.execute(signUpCommand);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserIdResponse(userIdCreated.value().toString()));
    }

}
