package com.nanna.portal.utlity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nanna.portal.domain.UserPrincipal;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.nanna.portal.constant.SecurityConstant.*;
import static java.util.Arrays.stream;
//import java.util.stream.Stream;


@Component
public class JWTTokenProvider {
    
    
    @Value ( "${jwt.secret}" )
    private String secret;
    
    //    generating jwt token
    
    public String generateJwtToken ( UserPrincipal userPrincipal ) {
        String[] claims = getClaimsFromUser ( userPrincipal );
        return JWT.create ().withIssuer ( GET_ARRAYS_LLC ).withAudience ( GET_ARRAYS_ADMINISTRATION ).withIssuedAt ( new Date () ).withSubject ( userPrincipal.getUsername () ).withArrayClaim ( AUTHORITIES , claims ).withExpiresAt ( new Date ( System.currentTimeMillis () + EXPIRATION_TIME ) ).sign ( HMAC512 ( secret.getBytes () ) );
    }
    
    
    public List < GrantedAuthority > getAuthorities ( String token ) {
        String[] claims = getclaimsForToken ( token );
        return stream ( claims ).map ( SimpleGrantedAuthority::new ).collect ( Collectors.toList () );
    }
    
    private String[] getclaimsForToken ( String token ) {
        JWTVerifier verifier = getJwtVerifier ();
        return verifier.verify ( token ).getClaim ( AUTHORITIES ).asArray ( String.class );
    }
//    get the jwt verifier
    private JWTVerifier getJwtVerifier ( ) {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = HMAC512 ( secret );
            verifier = JWT.require ( algorithm ).withIssuer ( GET_ARRAYS_LLC ).build ();
        }
        catch ( JWTVerificationException exception ) {
            throw new JWTVerificationException ( TOKEN_CANNOT_BE_VERIFIED );
        }
    
    
        return verifier;
    }
    
    public Authentication getAuthentication ( String username , List < GrantedAuthority > authorities , HttpServletRequest request ) {
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken ( username , null , authorities );
        authenticationToken.setDetails ( new WebAuthenticationDetailsSource ().buildDetails ( request ) );
        return authenticationToken;
    }
    
    public boolean isTokenvalid ( String username , String token ) {
        JWTVerifier verifier = getJwtVerifier ();
        return StringUtils.isNotEmpty ( username ) && IsTokenExpired ( verifier , token );
    }
    
    private boolean IsTokenExpired ( JWTVerifier verifier , String token ) {
        Date expiration = verifier.verify ( token ).getExpiresAt ();
        //        return expiration.before ( new Date () );
    
        return expiration.before ( new Date () );
        
    }
    
    
    public String getSubject ( String token ) {
        JWTVerifier verifier = getJwtVerifier ();
        return verifier.verify ( token ).getSubject ();
    
    }
    
    
    private String[] getClaimsFromUser ( UserPrincipal userPrincipal ) {
    
        List < String > authorities = new ArrayList <> ();
                for ( GrantedAuthority grantedAuthority : userPrincipal.getAuthorities () ) {
                    authorities.add ( grantedAuthority.getAuthority () );
                }

                return authorities.toArray ( new String[ 0 ] );
    }
    
    
    
//    public String[] getauthourities(UserPrincipal userPrincipal) {
//
//        List<String> authoritiesar = new ArrayList();
//        Collection < ? extends GrantedAuthority > authorities = userPrincipal.getAuthorities ();
//        for ( GrantedAuthority authority :authorities ) {
//            String stringAuthority = authority.getAuthority ();
//            authoritiesar.add ( stringAuthority );
//        }
//
//        return authoritiesar.toArray (new String[0]);
//    }
//
//
    
    
    
    
    
    
    
    
    
    
}
