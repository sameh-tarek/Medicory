package com.graduationProject.medicory.service.admin.users.pharmacy;

import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;

import java.util.List;

public interface AdminPharmacyService {

    PharmacyResponseDTO findPharmacyByUserCode(String userCode);
    PharmacyResponseDTO findPharmacyByUserEmail(String email);
    List<PharmacyResponseDTO> findPharmacyByName(String name);
    PharmacyDTO showAllDataOfPharmacyById(long id);
    String addPharmacy(PharmacyRequestDTO newPharmacy);
    String updatePharmacy(PharmacyDTO updatedPharmacy, Long pharmacyId);
    String deletePharmacy(Long pharmacyId);
}
