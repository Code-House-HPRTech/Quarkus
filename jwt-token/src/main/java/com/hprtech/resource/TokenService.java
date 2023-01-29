package com.hprtech.resource;


import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class TokenService {

    public String generateToken(){

        Set<String> roles = new HashSet<>(Arrays.asList("admin","teacher"));

       return Jwt.issuer("jwt-token")
                .subject("course")
                .groups(roles)
                .expiresAt(System.currentTimeMillis()+3600)
                .sign();
    }
}
