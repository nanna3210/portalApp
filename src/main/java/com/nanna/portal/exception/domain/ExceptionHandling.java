package com.nanna.portal.exception.domain;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.nanna.portal.domain.HttpResponse;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ErrorHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.Set;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
    
    private              Logger logger                    = LoggerFactory.getLogger ( getClass () );
    private final static String ACCOUNT_LOCKED            = "YOUR ACCOUNT IS LOCKED PLEASE CONTACT ADMINISTRATOR";
    private final static String METHOD_IS_NOT_ALLOWED     = "THIS REQUEST METHOD IS NOT ALLOWED ON THIS ENDPOINT PLEASE " + "SEND A '%s' REQUEST  ";
    private final static String INTERNAL_SERVER_ERROR_MSG = "AN ERROR OCCURRED WHILE PROCESSING THE REQUEST  ";
    private final static String INCORRECT_CREDENTIALS     = "USERNAME/ PASSWORD INCORRECT  PLEASE TRY AGAIN   ";
    
    private final static String ACCOUNT_DISABLED      = " YOUR ACCOUNT HAS BEEN DISABLED IF THIS IS AN ERROR PLEASE CONTACT" + " ADMINISTRATOR";
    private final static String ERROR_PROCESSING_FILE = "ERROR OCCURRED WHILE PROCESSING FILE  ";
    private final static String NOT_ENOUGH_PERMISSION = "YOU DO NOT HAVE PERMISSION ";
    public static final  String ERROR_PATH="/error";
    
    @ExceptionHandler ( DisabledException.class )
    public ResponseEntity < HttpResponse > accountDisableException ( ) {
        return createHttpResponse ( HttpStatus.BAD_REQUEST , ACCOUNT_DISABLED );
    }
    
    @ExceptionHandler ( BadCredentialsException.class )
    public ResponseEntity < HttpResponse > badCredentialException ( ) {
        
        return createHttpResponse ( HttpStatus.BAD_REQUEST , INCORRECT_CREDENTIALS );
    }
    
    @ExceptionHandler ( AccessDeniedException.class )
    public ResponseEntity < HttpResponse > accessDeniedException ( ) {
        return createHttpResponse ( HttpStatus.FORBIDDEN , NOT_ENOUGH_PERMISSION );
    }
    
    @ExceptionHandler ( LockedException.class )
    public ResponseEntity < HttpResponse > lockedException ( ) {
        return createHttpResponse ( HttpStatus.UNAUTHORIZED , ACCOUNT_LOCKED );
    }
    
    @ExceptionHandler ( TokenExpiredException.class )
    public ResponseEntity < HttpResponse > tokenExpiration ( TokenExpiredException exception ) {
        return createHttpResponse ( HttpStatus.UNAUTHORIZED , exception.getMessage ().toUpperCase () );
    }
    
    @ExceptionHandler ( EmailExistException.class )
    public ResponseEntity < HttpResponse > emailExistException ( EmailExistException exception ) {
        return createHttpResponse ( HttpStatus.BAD_REQUEST , exception.getMessage () );
    }
    
    @ExceptionHandler ( NoHandlerFoundException.class )
    public ResponseEntity < HttpResponse > nohandlerFound ( NoHandlerFoundException exception ) {
        return createHttpResponse ( HttpStatus.BAD_REQUEST , "This page Was not found" );
        
    }
    @ExceptionHandler ( UserNameExistException.class )
    public ResponseEntity < HttpResponse > userNameExistException ( UserNameExistException exception ) {
        return createHttpResponse ( HttpStatus.BAD_REQUEST , exception.getMessage () );
        
    }
    
    @ExceptionHandler ( UsernameNotFoundException.class )
    public ResponseEntity < HttpResponse > userNameNotFoundException ( UsernameNotFoundException exception ) {
        return createHttpResponse ( HttpStatus.BAD_REQUEST , exception.getMessage () );
    }
    
    public ResponseEntity < HttpResponse > emailNotFoundExceptin ( EmailNotFoundException exception ) {
        return createHttpResponse ( HttpStatus.BAD_REQUEST , exception.getMessage () );
    }
    
    @ExceptionHandler ( HttpRequestMethodNotSupportedException.class )
    public ResponseEntity < HttpResponse > httpMethodNotSupportedException ( HttpRequestMethodNotSupportedException exception ) {
        
        HttpMethod httpMethod = Objects.requireNonNull ( exception.getSupportedHttpMethods () ).iterator ().next ();
        return createHttpResponse ( HttpStatus.METHOD_NOT_ALLOWED , String.format ( METHOD_IS_NOT_ALLOWED , httpMethod ) );
    }
    
    
    @ExceptionHandler ( Exception.class )
    public ResponseEntity < HttpResponse > internalServerErrorException ( Exception exception ) {
        
        logger.error ( exception.getMessage () );
        return createHttpResponse ( HttpStatus.INTERNAL_SERVER_ERROR , INTERNAL_SERVER_ERROR_MSG );
        
    }
    
    @ExceptionHandler ( NoResultException.class )
    public ResponseEntity < HttpResponse > noResultException ( NoResultException exception ) {
        logger.error ( exception.getMessage () );
        return createHttpResponse ( HttpStatus.NOT_FOUND , exception.getMessage () );
        
        
    }
    
    @ExceptionHandler ( IOException.class )
    public ResponseEntity < HttpResponse > ioException ( IOException exception ) {
        logger.error ( exception.getMessage () );
        return createHttpResponse ( HttpStatus.INTERNAL_SERVER_ERROR , ERROR_PROCESSING_FILE );
        
    }
    
    
    private ResponseEntity < HttpResponse > createHttpResponse ( HttpStatus httpStatus , String message ) {
        HttpResponse httpResponse = new HttpResponse ( httpStatus.value () , httpStatus , httpStatus.getReasonPhrase ().toUpperCase () , message.toUpperCase () );
        return new ResponseEntity <> ( httpResponse , httpStatus );
        
    }
    
    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse ( HttpStatus.NOT_FOUND , "no Mapping for this url " );
    }
    
    
    
    public String getErrorPath() {
        return ERROR_PATH;
    }
    
}
