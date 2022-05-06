package com.cms.portal.patient.service;

import com.cms.portal.patient.request.JwtRequest;
import com.cms.portal.patient.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtService extends UserDetailsService {

    JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;

}
