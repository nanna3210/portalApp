package com.nanna.portal.resource;

import com.nanna.portal.domain.User;
import com.nanna.portal.exception.domain.EmailExistException;
import com.nanna.portal.exception.domain.ExceptionHandling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/","/user" })
public class UserResource extends ExceptionHandling {

    @GetMapping("/home")
    public String  showUser() throws EmailExistException {
//        return "application working";
        throw  new EmailExistException ( "email already exists " );
    
    }



}
