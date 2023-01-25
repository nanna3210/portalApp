package com.nanna.portal.service.impl;

import com.nanna.portal.domain.User;
import com.nanna.portal.domain.UserPrincipal;
import com.nanna.portal.repository.UserRepository;
import com.nanna.portal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    
    private Logger logger = LoggerFactory.getLogger ( getClass () );
    
    
    private UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl ( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername ( username );
        if(user== null ) {
    
            logger.error ( "user not found for This username " + username );
    
            throw new UsernameNotFoundException ( "user not found for This username " + username );
            
        }else {
            user.setLastLoginDateDisplay ( user.getLastLoginDate () );
            user.setLastLoginDate ( new Date () );
            userRepository.save ( user );
    
            UserPrincipal userPrincipal = new UserPrincipal ( user );
            logger.info ( "returning found user by username "+ username );
            return userPrincipal;
        }
        
        
    }
}
