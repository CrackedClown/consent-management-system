package com.cms.cm.service;

import com.cms.cm.request.JwtRequest;
import com.cms.cm.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtService extends UserDetailsService {

    JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;

}
