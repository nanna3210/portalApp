package com.nanna.portal.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanna.portal.constant.SecurityConstant;
import com.nanna.portal.domain.HttpResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.OutputStream;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    
    @Override
    public void handle ( HttpServletRequest request , HttpServletResponse response ,
            AccessDeniedException exception ) throws IOException, ServletException {
        HttpResponse httpResponse = new HttpResponse ( HttpStatus.UNAUTHORIZED.value () , HttpStatus.UNAUTHORIZED , HttpStatus.UNAUTHORIZED.getReasonPhrase () , SecurityConstant.ACCESS_DENIED );
        response.setStatus ( HttpStatus.UNAUTHORIZED.value () );
        response.setContentType ( MediaType.APPLICATION_JSON_VALUE );
        OutputStream outputStream = response.getOutputStream ();
        ObjectMapper objectMapper = new ObjectMapper ();
        objectMapper.writeValue ( outputStream,httpResponse );
        outputStream.flush ();
    }
}
