package com.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.model.Userlogin;
import com.repository.UserRightsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

	private final UserRightsRepository userRightsRepository;

    public boolean hasPermission(Authentication authentication,Integer pagetypeid,String operation) 
    {
        // Check whether user is authenticated
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        
        // Get logged-in user
        Userlogin user = (Userlogin) authentication.getPrincipal();

        // Get userid from logged-in user
        String userid = String.valueOf(user.getUserid());

        // Check permission in database
        int count = userRightsRepository.checkPermission(userid,pagetypeid,operation);

        return count > 0;
    }
}
