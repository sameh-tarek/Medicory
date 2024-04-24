package com.sameh.medicory.model.emergency;

import com.sameh.medicory.entity.enums.BloodType;
import com.sameh.medicory.entity.phoneEntities.RelativePhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyDTO {
    private String ownerName;
    private BloodType bloodType;
    private List< String> chronicDiseases;
    private List<String> allergies;
    private List<String> surgeries;
    private List<RelativePhoneNumber> relativePhoneNumbers;
}
