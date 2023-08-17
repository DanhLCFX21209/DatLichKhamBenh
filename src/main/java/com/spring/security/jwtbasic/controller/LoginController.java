package com.spring.security.jwtbasic.controller;
import com.spring.security.jwtbasic.config.TokenManager;
import com.spring.security.jwtbasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spring.security.jwtbasic.util.JwtRequestModel;
import com.spring.security.jwtbasic.util.JwtResponseModel;
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> createToken(@RequestBody JwtRequestModel request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (DisabledException e) {
            ApiResponse response = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "USER_DISABLED: The user account is disabled.", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (BadCredentialsException e) {
            ApiResponse response = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "INVALID_CREDENTIALS: Invalid email or password.", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (AuthenticationException e) {
            ApiResponse response = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "AUTHENTICATION_ERROR: Authentication failed.", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        if (userDetails == null) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "USER_NOT_FOUND: User with the provided email not found.", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Authentication successful.", new JwtResponseModel(jwtToken));
        return ResponseEntity.ok(response);
    }

}