package com.graduationProject.medicory.service.doctor.impl;

import com.graduationProject.medicory.entity.medicationEntities.Medicine;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.repository.MedicationsRepositories.MedicineRepository;
import com.graduationProject.medicory.service.doctor.DoctorMedicineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DoctorMedicineServiceImpl implements DoctorMedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public List<Medicine> searchForMedicine(String name) {
        log.info("Doctor want to search for medicine by Some letters: {}", name);
        List<Medicine> medicines = medicineRepository.findByNameContainingIgnoreCase(name);
        log.trace("Selected Medicines from search {}", medicines);
        return medicines;
    }

    @Override
    public Medicine findMedicineById(Long id) {
        log.info("Doctor want to search for medicine by Id: {}", id);
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This medicine with id "+ id + " doesn't exist!"));
        log.trace("This Medicine details {}", medicine);
        return medicine;
    }
}
