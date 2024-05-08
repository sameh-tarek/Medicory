package com.graduationProject.medicory.service.card;

import com.graduationProject.medicory.entity.enums.Role;
import com.graduationProject.medicory.utils.OwnerContext;
import com.graduationProject.medicory.utils.SecurityUtils;
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
    public Role getInteractingRoleBasedOnCard(Long ownerId) {

        Long authenticatedUserId = securityUtils.getCurrentAuthenticatedOwnerId();

        if (authenticatedUserId.equals(ownerId)) {
            return Role.OWNER;
        } else {
            Role authenticatedUserRole = securityUtils.getCurrentAuthenticatedUserRole();
            if (authenticatedUserRole == Role.CLINIC ||
                    authenticatedUserRole == Role.HOSPITAL ||
                    authenticatedUserRole == Role.LAB ||
                    authenticatedUserRole == Role.PHARMACY ||
                    authenticatedUserRole == Role.DOCTOR ||
                    authenticatedUserRole ==Role.ADMIN
            ) {
                return authenticatedUserRole;
            } else {
                return Role.EMERGENCY;
            }
        }
    }
}
