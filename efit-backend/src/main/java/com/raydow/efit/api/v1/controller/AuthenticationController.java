package com.raydow.efit.api.v1.controller;

import com.raydow.efit.api.v1.dto.AuthenticationRequestDTO;
import com.raydow.efit.api.v1.dto.user.UserCreateDTO;
import com.raydow.efit.api.v1.mapper.UserMapperDTO;
import com.raydow.efit.service.impl.AuthenticationServiceImpl;
import com.raydow.efit.service.vo.AuthenticationResponseVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.raydow.efit.api.v1.controller.RestPath.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH + "/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl service;
    private final UserMapperDTO userMapperDTO;

    public AuthenticationController(AuthenticationServiceImpl service, UserMapperDTO userMapperDTO) {
        this.service = service;
        this.userMapperDTO = userMapperDTO;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseVO> register(
            @RequestBody UserCreateDTO userCreateDTO
    ) {
        return ResponseEntity.ok(service.register(userMapperDTO.fromCreateDtoToVO(userCreateDTO)));
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