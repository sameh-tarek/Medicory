package com.graduationProject.medicory.service.card;

import com.graduationProject.medicory.entity.enums.Role;

public interface CardService {
    Role getInteractingRoleBasedOnCard(Long ownerId);
}
