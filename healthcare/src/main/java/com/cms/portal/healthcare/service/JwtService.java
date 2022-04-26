package com.cms.portal.healthcare.service;

import com.cms.portal.healthcare.request.JwtRequest;
import com.cms.portal.healthcare.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtService extends UserDetailsService {

    JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;

}
