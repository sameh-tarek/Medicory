package com.graduationProject.medicory.service.doctor;

import com.graduationProject.medicory.entity.medicationEntities.Medicine;

import java.util.List;

public interface DoctorMedicineService {
    List<Medicine> searchForMedicine(String name);

    Medicine findMedicineById(Long id);
}
