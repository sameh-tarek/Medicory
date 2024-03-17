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

    public Owner getCurrentOwner(Long ownerId){
        return ownerRepository.findById(ownerId).
                orElseThrow(() -> new RecordNotFoundException("This user with id " + ownerId + " doesn't exist"));
    }

    public User getCurrentUser(Long ownerId){
        return getCurrentOwner(ownerId).getUser();
    }
}
