package com.jmiranda.identity.infrastructure.user.web;

import com.jmiranda.identity.application.user.create.CreateHumanUser;
import com.jmiranda.identity.application.user.create.CreateHumanUserCommand;
import com.jmiranda.identity.domain.user.model.UserId;
import com.jmiranda.identity.infrastructure.user.web.dto.request.CreateHumanUserRequest;
import com.jmiranda.identity.infrastructure.user.web.dto.response.UserIdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateHumanUser createHumanUser;

    public UserController(CreateHumanUser createHumanUser) {
        this.createHumanUser = createHumanUser;
    }

    @PostMapping
    public ResponseEntity<UserIdResponse> create(
            @RequestBody CreateHumanUserRequest request
    ) {

        CreateHumanUserCommand command =
                new CreateHumanUserCommand(
                        request.firstName(),
                        request.lastName(),
                        request.personalEmail(),
                        request.institutionalEmail(),
                        request.phoneNumber(),
                        request.birthDate(),
                        request.identificationTypeId(),
                        request.identificationCode()
                );

        UserId userId = createHumanUser.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserIdResponse(userId.value().toString()));
    }
}
