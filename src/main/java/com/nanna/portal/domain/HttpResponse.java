package com.nanna.portal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import java.util.Date;


public class HttpResponse {

    
    private int HtpStatusCode;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss" , timezone = "Asia/Kolkata")
    private Date timeStamp;
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
        this.timeStamp = new Date ();
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
    
    public Date getTimeStamp ( ) {
        return timeStamp;
    }
    
    public void setTimeStamp ( Date timeStamp ) {
        this.timeStamp = timeStamp;
    }
    
}
