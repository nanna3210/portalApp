package com.nanna.portal.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class UserPrincipal implements UserDetails {

//    implementing UserDetails spring security
    private User user;
    
//    constructor and passing the entity there
    public UserPrincipal ( User user ) {
        this.user = user;
    }
    
    @Override
    public Collection < ? extends GrantedAuthority > getAuthorities ( ) {
//        SimpleGrantedAuthority is extending granted authority
//        mapping strings array  new simpleGrantedAuthority object
//        converting them to collection
        return stream(this.user.getAuthorities ()).map ( SimpleGrantedAuthority::new ).collect( Collectors.toList ());
    
    }
//    getter and setter method
    @Override
    public String getPassword ( ) {
        return this.user.getPassword ();
    }
    
    @Override
    public String getUsername ( ) {
        return this.user.getUsername ();
    }
    
    @Override
    public boolean isAccountNonExpired ( ) {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked ( ) {
        return this.user.isNotLocked ();
    }
    
    @Override
    public boolean isCredentialsNonExpired ( ) {
        return true;
    }
    
    @Override
    public boolean isEnabled ( ) {
        return this.user.isActive ();
    }
}
