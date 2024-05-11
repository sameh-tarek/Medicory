package com.graduationProject.medicory.utils;

import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerContext {

    private final OwnerRepository ownerRepository;
    //TODO when we use the card we will fetch the id from the card with card/scan endpoint

    public Owner getCurrentOwner(String userCode){
        return ownerRepository.findByUserCode(userCode).
                orElseThrow(() -> new RecordNotFoundException("This user with code " + userCode + " doesn't exist"));
    }

    public User getCurrentUser(String userCode){
        return getCurrentOwner(userCode).getUser();
    }
}
