package com.playtech.bookstore.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenProvider {
    private static final byte[] keyBytes = Decoders.BASE64.decode("YXNkZmdoamtsw7ZsamV5NjdneTMwNGowb2JnaHZneXViam5pbml1bmlidWJ1aGI");
    public static final Key key = Keys.hmacShaKeyFor(keyBytes);
}
