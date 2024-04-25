package com.sameh.medicory.controller.cardscan;

import com.sameh.medicory.entity.enums.Role;
import com.sameh.medicory.service.card.CardService;
import com.sameh.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/scan")
    public String scanCard(@RequestParam("userCode") String userCode) {
        return userCode;
    }

    @PostMapping("/interacting-role/{ownerId}")
    public ResponseEntity<Role> getInteractingRoleBasedOnCard(@PathVariable Long ownerId) {
        Role interactingRole = cardService.getInteractingRoleBasedOnCard(ownerId);
        return ResponseEntity.ok(interactingRole);
    }
}
