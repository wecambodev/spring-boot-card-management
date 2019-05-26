package com.mbanq.banksystem.security.service;

import com.mbanq.banksystem.model.User;
import com.mbanq.banksystem.repository.ConsumerRepository;
import com.mbanq.banksystem.security.JwtTokenUtil;
import com.mbanq.banksystem.security.JwtUser;
import com.mbanq.banksystem.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = consumerRepository.findByPhoneNumber(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }


    public JwtUser getUserFromHeaderToken(HttpServletRequest request) {

        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) this.loadUserByUsername(username);
    }
}
