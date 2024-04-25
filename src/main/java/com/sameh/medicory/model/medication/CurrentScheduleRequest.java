package com.sameh.medicory.model.medication;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.entity.usersEntities.Owner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.List;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class CurrentScheduleRequest {
//    private MedicationDTO updatesInMedication;
    private Long id;
    private String medicineName;
    private String dose;
    private int frequency;
    private String sideEffects;
    private String tips;
}

