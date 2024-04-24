package com.sameh.medicory.utils;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.repository.OwnerRepository;
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
