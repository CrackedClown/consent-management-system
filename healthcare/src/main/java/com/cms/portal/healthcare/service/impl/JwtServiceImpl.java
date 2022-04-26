package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import com.cms.portal.healthcare.repository.HealthcareProfessionalRepository;
import com.cms.portal.healthcare.request.JwtRequest;
import com.cms.portal.healthcare.response.JwtResponse;
import com.cms.portal.healthcare.service.JwtService;
import com.cms.portal.healthcare.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Autowired
    private HealthcareProfessionalRepository healthcareProfessionalRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
        authenticate(username, password);

        final UserDetails userDetails = loadUserByUsername(username);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        HealthcareProfessional user = healthcareProfessionalRepository.findByUsername(username);

        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HealthcareProfessional user = healthcareProfessionalRepository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
        } else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private Set getAuthorities(HealthcareProfessional user) {
        Set authorities = new HashSet();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
        });
        return authorities;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User is disabled!");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials from user!");
        }
    }
}
