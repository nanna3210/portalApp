package com.nanna.portal.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanna.portal.constant.SecurityConstant;
import com.nanna.portal.domain.HttpResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
    
    
    @Override
    public void commence ( HttpServletRequest request , HttpServletResponse response ,
            AuthenticationException exception ) throws IOException {
        
        HttpResponse httpResponse = new HttpResponse ( HttpStatus.FORBIDDEN.value () , HttpStatus.FORBIDDEN , HttpStatus.FORBIDDEN.getReasonPhrase () , SecurityConstant.FORBIDDEN_MESSAGE );
        response.setContentType ( APPLICATION_JSON_VALUE );
        response.setStatus (HttpStatus.FORBIDDEN.value () );
        ServletOutputStream outputStream = response.getOutputStream ();
        ObjectMapper objectMapper = new ObjectMapper ();
        objectMapper.writeValue ( outputStream, httpResponse );
        outputStream.flush ();
    }
}
