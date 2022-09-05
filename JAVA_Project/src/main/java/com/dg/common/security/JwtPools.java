package com.dg.common.security;

/**
 * @author The Fool
 */
public interface JwtPools {
    String TOKEN_HEADER = "Authorization";

    String SECRET = "secret";

    Long EXPIRATION = 60 * 60 * 24L;

    String TOKEN_HEAD = "Bearer";

}
