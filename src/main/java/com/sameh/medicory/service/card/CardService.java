package com.sameh.medicory.service.card;

import com.sameh.medicory.entity.enums.Role;

public interface CardService {
    Role getInteractingRoleBasedOnCard();
}
