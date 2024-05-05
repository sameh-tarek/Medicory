package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Lab;
import com.graduationProject.medicory.mapper.LabMapper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabMappperImpl implements LabMapper {

    private final UserMapper map;

    @Override
    public Lab toEntity(LabRequestDTO labRequestDTO) {
        return new Lab(
                labRequestDTO.getId()
                , labRequestDTO.getName()
                , labRequestDTO.getGoogleMapsLink()
                , labRequestDTO.getAddress()
                , labRequestDTO.getOwnerName()
                , map.toEntity(
                labRequestDTO.getUser()
        )
        );
    }

    @Override
    public LabRequestDTO toDTO(Lab lab) {
        return new LabRequestDTO(
                lab.getId(),
                lab.getName(),
                lab.getGoogleMapsLink(),
                lab.getAddress(),
                lab.getOwnerName(),
                map.toDto(
                        lab.getUser()
                )
        );
    }

    @Override
    public LabResponseDTO toResponseDTO(Lab lab) {
        return new LabResponseDTO(
                lab.getId(),
                lab.getName(),
                lab.getUser().isEnabled()
        );
    }
}