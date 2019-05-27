package com.mbanq.banksystem.controller;

import com.mbanq.banksystem.dto.AuthSignInRequest;
import com.mbanq.banksystem.dto.AuthSignUpRequest;
import com.mbanq.banksystem.exception.ValidationException;
import com.mbanq.banksystem.model.User;
import com.mbanq.banksystem.security.JwtTokenUtil;
import com.mbanq.banksystem.security.service.JwtUserDetailsService;
import com.mbanq.banksystem.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/api/auth")
@Api(tags = "Auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;




    /**
     *
     *  Singin
     *  by phone number and password
     *
     *
     * **/
    @PostMapping( value = "/signin")
    @ApiResponses(value = {

        @ApiResponse(code = 400, message = "Input invalid, Validation"),

        @ApiResponse(code = 403, message = "Access denied"),

        @ApiResponse(code = 422, message = "PhoneNumber and Password incorrect"),

        @ApiResponse(code = 500, message = "Expired or invalid JWT token")
    })
    public ResponseEntity<?> signIn(@Valid @RequestBody AuthSignInRequest request, BindingResult result) {

        if(result.hasErrors()) {
            throw new ValidationException("Invalid signin ", result);
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));
        } catch (DisabledException | BadCredentialsException e ) {

            HashMap res = new HashMap();
            res.put("message", "Your phone number and password incorrect!");
            res.put("status_code", 422);

            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getPhoneNumber());
        final String token = jwtTokenUtil.generateToken(userDetails);

        HashMap res = new HashMap();
        res.put("token", token);
        res.put("status_code", 200);

        return new ResponseEntity(res, HttpStatus.OK);
    }


    /**
     *
     *  SingUp
     *  by phone number and  password with role
     *
     *
     * **/
    @PostMapping( value = "/signup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation, Signup successful"),
            @ApiResponse(code = 400, message = "Input invalid, Validation"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})

    public ResponseEntity<?> signUp(@Valid @RequestBody AuthSignUpRequest request, BindingResult result) {

        if(result.hasErrors()) {
            throw new ValidationException("Signup Invalid", result);
        }

        User consumer =  modelMapper.map(request, User.class);

        if(consumerService.existsByPhoneNumber(consumer.getPhoneNumber())) {
            HashMap res = new HashMap();
            res.put("message", "This phone number already in used");
            res.put("status_code", 422);

            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        } else {

            if(consumerService.save(consumer)) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getPhoneNumber());
                final String token = jwtTokenUtil.generateToken(userDetails);

                HashMap res = new HashMap();
                res.put("message", "Operation, Signup successful");
                res.put("status_code", 200);
                res.put("token", token);

                return new ResponseEntity(res, HttpStatus.CREATED);
            }
        }

        HashMap res = new HashMap();
        res.put("message", "Signup not success, Try again.");
        res.put("status_code", 400);

        return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
    }




}
