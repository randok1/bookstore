package com.playtech.bookstore.configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenProvider {
    private static final byte[] keyBytes = Decoders.BASE64.decode("c2FmZ2hqZ2tsa2poZ2ZkZ2zDtmdkbGtzZ25kc2tsbGduZHNrbGZmZG5oa2dkc2duZmfDtmRkZw");
    public static final Key key = Keys.hmacShaKeyFor(keyBytes);
}
