package com.sameh.medicory.service.card;

import com.sameh.medicory.entity.enums.Role;
import com.sameh.medicory.utils.OwnerContext;
import com.sameh.medicory.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final OwnerContext ownerContext;
    private final SecurityUtils securityUtils;

    @Override
    public Role getInteractingRoleBasedOnCard() {

        Long authenticatedUserId = securityUtils.getCurrentAuthenticatedOwnerId();
        Long cardOwnerId = ownerContext.getOwnerId();

        if (authenticatedUserId.equals(cardOwnerId)) {
            return Role.OWNER;
        } else {
            Role authenticatedUserRole = securityUtils.getCurrentAuthenticatedUserRole();
            if (authenticatedUserRole == Role.CLINIC ||
                    authenticatedUserRole == Role.HOSPITAL ||
                    authenticatedUserRole == Role.LAB ||
                    authenticatedUserRole == Role.PHARMACY) {
                return authenticatedUserRole;
            } else {
                return Role.EMERGENCY;
            }
        }
    }
}
