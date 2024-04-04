package com.sameh.medicory.service.owner;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.model.owner.OwnerDTO;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    OwnerDTO getOwnerPersonalInformation(long id);
}
