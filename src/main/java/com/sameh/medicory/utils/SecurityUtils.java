package com.sameh.medicory.utils;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.repository.OwnerRepository;
import com.sameh.medicory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    public User getCurrentUser (){
        // TODO get authenticated user id
        Long userId = 1L;
        User patientUser =  userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("This User with Id " + userId + " doesn't exist"));
        return patientUser;
    }
    public Owner getCurrentOwner(){
        // TODO get authenticated user id
        Long userId = 1L;
        Owner patientOwner = ownerRepository.findAllByUserId(userId)
                .orElseThrow(() -> new RecordNotFoundException("This Patient with userId " + userId + " doesn't exist"));
        return patientOwner;
    }

}
