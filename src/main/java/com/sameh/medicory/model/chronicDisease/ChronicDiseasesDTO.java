package com.sameh.medicory.model.chronicDisease;

import com.sameh.medicory.entity.usersEntities.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChronicDiseasesDTO {
    private String name;
    private String information;
    private Long ownerId;
}
