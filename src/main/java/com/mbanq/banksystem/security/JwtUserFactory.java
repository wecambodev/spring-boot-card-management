package com.mbanq.banksystem.security;

import com.mbanq.banksystem.model.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getRole(),
                user.getStatus().equals("1") ? true: false
        );
    }
}
