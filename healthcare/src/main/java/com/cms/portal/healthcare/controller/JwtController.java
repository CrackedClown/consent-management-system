package com.cms.portal.healthcare.controller;

import com.cms.portal.healthcare.request.JwtRequest;
import com.cms.portal.healthcare.response.JwtResponse;
import com.cms.portal.healthcare.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
