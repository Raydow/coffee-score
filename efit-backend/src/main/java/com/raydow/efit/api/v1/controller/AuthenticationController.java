package com.raydow.efit.api.v1.controller;

import com.raydow.efit.api.v1.dto.AuthenticationRequestDTO;
import com.raydow.efit.service.impl.AuthenticationServiceImpl;
import com.raydow.efit.service.vo.AuthenticationResponseVO;
import com.raydow.efit.service.vo.RegisterRequestVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl service;

    public AuthenticationController(AuthenticationServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseVO> register(
            @RequestBody RegisterRequestVO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseVO> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}