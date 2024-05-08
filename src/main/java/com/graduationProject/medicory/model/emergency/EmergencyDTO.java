package com.graduationProject.medicory.model.emergency;

import com.graduationProject.medicory.entity.phoneEntities.RelativePhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyDTO {
    private String ownerName;
    private String bloodType;
    private List< String> chronicDiseases;
    private List<String> allergies;
    private List<String> surgeries;
    private List<RelativePhoneNumber> relativePhoneNumbers;
}
