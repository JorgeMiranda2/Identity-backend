package com.jmiranda.identity.infrastructure.role.web;


import com.jmiranda.identity.infrastructure.role.web.dto.request.CreateRoleRequest;
import com.jmiranda.identity.infrastructure.role.web.dto.response.RoleIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    public RoleController() {

    }

    @PostMapping
    public ResponseEntity<RoleIdResponse> create(
            @RequestBody CreateRoleRequest request
    ) {
        return null;
    }



}
