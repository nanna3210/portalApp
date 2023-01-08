package com.nanna.portal.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private Long  id;
    private String userId;
    private String firstName;
    private String lastName ;
    private String username;
    private String password;
   private String email ;
   private String profileImageUrl;
   private Date lastLoginDate;
   private Date LastLoginDateDisplay;
   private Date joinDate ;
   private String [] roles; // roles user , roles Admin
  private String [] authorities ;
  private boolean isActive;
  private  boolean isNotLocked;
    
    
}
