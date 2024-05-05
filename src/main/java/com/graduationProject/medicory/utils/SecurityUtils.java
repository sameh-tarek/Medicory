package com.graduationProject.medicory.utils;
import com.graduationProject.medicory.entity.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public String getCurrentAuthenticatedUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return email;
    }
    public Long getCurrentAuthenticatedOwnerId(){
        //TODO i will update it When add Security
        return 2L;
    }

    public Role getCurrentAuthenticatedUserRole(){
        //TODO i will update it When add Security
        return Role.CLINIC;
    }
}
