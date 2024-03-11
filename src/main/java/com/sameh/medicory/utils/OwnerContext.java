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
    private Long ownerId = 1L;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Owner getCurrentOwner(){
        return ownerRepository.findById(getOwnerId()).
                orElseThrow(() -> new RecordNotFoundException("This user with id " + getOwnerId() + " doesn't exist"));
    }

    public User getCurrentUser(){
        return getCurrentOwner().getUser();
    }
}
