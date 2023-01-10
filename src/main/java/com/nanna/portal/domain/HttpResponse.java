package com.nanna.portal.domain;

import org.springframework.http.HttpStatus;


public class HttpResponse {

    
    private int HtpStatusCode;
  
    private HttpStatus httpStatus;
    private String reason;
    private String message;
    
    public HttpResponse ( ) {
    }
    
    
    public HttpResponse ( int htpStatusCode , HttpStatus httpStatus , String reason , String message ) {
        HtpStatusCode   = htpStatusCode;
        this.httpStatus = httpStatus;
        this.reason     = reason;
        this.message    = message;
    }
    
    public int getHtpStatusCode ( ) {
        return HtpStatusCode;
    }
    
    public void setHtpStatusCode ( int htpStatusCode ) {
        HtpStatusCode = htpStatusCode;
    }
    
    public HttpStatus getHttpStatus ( ) {
        return httpStatus;
    }
    
    public void setHttpStatus ( HttpStatus httpStatus ) {
        this.httpStatus = httpStatus;
    }
    
    public String getReason ( ) {
        return reason;
    }
    
    public void setReason ( String reason ) {
        this.reason = reason;
    }
    
    public String getMessage ( ) {
        return message;
    }
    
    public void setMessage ( String message ) {
        this.message = message;
    }
}
