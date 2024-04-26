package com.sameh.medicory.service.admin.users;

import com.sameh.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.sameh.medicory.model.users.pharmacy.PharmacyResponseDTO;

import java.util.List;

public interface AdminPharmacyService {

    PharmacyResponseDTO findPharmacyByUserCode(String userCode);
    PharmacyResponseDTO findPharmacyByUserEmail(String email);
    List<PharmacyResponseDTO> findPharmacyByName(String name);
    PharmacyRequestDTO showAllDataOfPharmacyById(long id);
    String addPharmacy(PharmacyRequestDTO newPharmacy);
    String updatePharmacy(PharmacyRequestDTO updatedPharmacy, Long pharmacyId);
    String deletePharmacy(Long pharmacyId);
}
