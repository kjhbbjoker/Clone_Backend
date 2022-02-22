package com.example.clone.security.jwt;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.clone.security.UserDetailsImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 3일 (단위: seconds)
    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String JWT_SECRET = "5mini";
    public static final String UID = "UID";
    public static final String NICKNAME = "NICKNAME";


    public static String generateJwtToken(UserDetailsImpl userDetails) {
        String token = null;
        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put("typ", "JWT");
            headers.put("alg", "HS256");

            token = JWT.create()
                    .withHeader(headers)
                    .withIssuer("sparta")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                    .withClaim(UID, userDetails.getUsername())
                    .withClaim(UID, Long.toString(userDetails.getUser().getUserId()))
                    .withClaim(NICKNAME, userDetails.getUser().getNickname())
                    .sign(generateAlgorithm());

            System.out.println(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }
}
