package com.graduationProject.medicory.utils;
import com.graduationProject.medicory.entity.enums.Role;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.repository.usersRepositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final UserRepository userRepository;

    public String getCurrentAuthenticatedUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return email;
    }

    public User getCurrentAuthenticatedUserByEmail() {
        String authenticatedUserEmail = getCurrentAuthenticatedUserEmail();
        User user = userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new UsernameNotFoundException("This user with email "+ authenticatedUserEmail + " doesn't exist!"));

        return user;
    }
    public String getCurrentAuthenticatedUserCode(){
        return getCurrentAuthenticatedUserByEmail().getCode();
    }

    public Role getCurrentAuthenticatedUserRole(){
        return getCurrentAuthenticatedUserByEmail().getRole();
    }
}
