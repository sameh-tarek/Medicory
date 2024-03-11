package com.sameh.medicory.controller.cardscan;

import com.sameh.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("card")
@RequiredArgsConstructor
public class CardController {
    private final OwnerContext ownerContext;
    @PostMapping("/scan")
    public ResponseEntity<String> scanCard(@RequestParam("ownerId") Long ownerId) {
        ownerContext.setOwnerId(ownerId);
        return ResponseEntity.ok("card scanned successfully");
    }
}
