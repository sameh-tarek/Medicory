package com.graduationProject.medicory.utils;
import com.graduationProject.medicory.entity.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public String getCurrentAuthenticatedUserEmail(){
        //TODO we will use it when add spring security to get info about current authenticated user
        return null;
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
