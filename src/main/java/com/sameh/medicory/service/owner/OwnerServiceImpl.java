package com.sameh.medicory.service.owner;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.OwnerMapper;
import com.sameh.medicory.model.owner.OwnerDTO;
import com.sameh.medicory.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public OwnerDTO getOwnerPersonalInformation(long id) {
        Owner owner= ownerRepository.findAllByUserId(id)
                .orElseThrow(()-> new RecordNotFoundException("Owner with id " + id + " doesn't exist!"));
        return ownerMapper.toDTO(owner);
    }

}
