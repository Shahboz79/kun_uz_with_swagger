package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.dto.RegistrationDTO;
import com.company.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "Auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // private Logger log = LoggerFactory.getLogger(AuthController.class);
    @ApiOperation(value = "Login", notes = "Method used for login and getting token.", nickname = "Mazgi")
    @PostMapping("/login")
    public ResponseEntity<?> create(@RequestBody @Valid AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @ApiOperation(value = "Registration", notes = "Method used for registration", nickname = "Mazgi")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDTO dto) {
        log.trace("Registration:  {}", dto);
        authService.registration(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Email Verification", notes = "Method used Email verification")
    @GetMapping("/verification/{jwt}")
    public ResponseEntity<?> verification(@PathVariable("jwt") String jwt) {
        authService.verification(jwt);
        return ResponseEntity.ok().build();
    }

}
