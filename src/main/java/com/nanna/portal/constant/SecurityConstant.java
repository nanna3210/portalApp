package com.nanna.portal.constant;

public class SecurityConstant {
    
    public static final long EXPIRATION_TIME = 432_000_000;
    public static final String TOKEN_PREFIX = "Bear ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Cannot Be Verified";
    public static final String GET_ARRAYS_LLC = "Get Arrays , LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management  Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "you need to login to access this page";
    public static final String ACCESS_DENIED = "YOU DO NOT HAVE PERMISSION TO  ACCESS THIS PAGE ";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS ";
    public static final String[] PUBLIC_URLS = { "/user/login" , "/user/register" , "/user/resetpassword/**" ,"/user" +
            "/image/**"};
    
}
