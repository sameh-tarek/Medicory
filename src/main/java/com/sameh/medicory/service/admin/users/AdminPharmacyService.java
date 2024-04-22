package com.sameh.medicory.service.admin.users;

import com.sameh.medicory.model.users.PharmacyDTO;

import java.util.List;

public interface AdminPharmacyService {
    List<PharmacyDTO> showAllPhrmacies();
    PharmacyDTO findPharmacyById(Long pharmacyId);
    PharmacyDTO findPharmcyByEmail(String userEmail);
    List<PharmacyDTO> findPharmacyByName(String PhrmacyName);
    String addPharmacy(PharmacyDTO newPharmacy);
    String UpdatePharmacy(PharmacyDTO updatedPharmacy,Long pharmacyId);
    String deletePharmacy(Long pharmacyId);
}
