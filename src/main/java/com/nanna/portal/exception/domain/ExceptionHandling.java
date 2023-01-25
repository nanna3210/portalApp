package com.nanna.portal.exception.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {
    
    private Logger logger = LoggerFactory.getLogger ( getClass () );
    private final static String ACCOUNT_LOCKED= "YOUR ACCOUNT IS LOCKED PLEASE CONTACT ADMINISTRATOR";
    private final static String  METHOD_IS_NOT_ALLOWED= "THIS REQUEST METHOD IS NOT ALLOWED ON THIS ENDPOINT PLEASE " + "SEND A '%s' REQUEST  ";
    private final static String INTERNAL_SERVER_ERROR_MSG = "AN ERROR OCCURRED WHILE PROCESSING THE REQUEST  ";
    private final static String INCORRECT_CREDENTIALS = "USERNAME/ PASSWORD INCORRECT  PLEASE TRY AGAIN   ";
    
    private final static String ACCOUNT_DISABLED = " YOUR ACCOUNT HAS BEEN DISABLED IF THIS IS AN ERROR PLEASE CONTACT" + " ADMINISTRATOR";
    private final static String ERROR_PROCESSING_FILE = "ERROR OCCURRED WHILE PROCESSING FILE  ";
    private final static String NOT_ENOUGH_PERMISSION = "YOU DO NOT HAVE PERMISSION ";
}
