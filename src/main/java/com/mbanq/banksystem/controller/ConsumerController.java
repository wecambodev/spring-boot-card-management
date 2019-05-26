package com.mbanq.banksystem.controller;
import com.mbanq.banksystem.security.JwtTokenUtil;
import com.mbanq.banksystem.security.JwtUser;
import com.mbanq.banksystem.security.service.JwtUserDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/api/consumer")
@Api(tags = "Consumer")
public class ConsumerController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private JwtUserDetailsService userDetailsService;


    @GetMapping(value = "/me")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {

        JwtUser user =  userDetailsService.getUserFromHeaderToken(request);
        return user;
    }

}
