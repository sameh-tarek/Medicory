package com.graduationProject.medicory.service.admin.users.owner;


import com.graduationProject.medicory.model.users.owner.OwnerRequestDTO;
import com.graduationProject.medicory.model.users.owner.OwnerResponseDTO;

import java.util.List;

public interface AdminOwnerService {
    List<OwnerResponseDTO> findOwnersByOwnerName(String fullName);
    OwnerResponseDTO findOwnerByOwnerEmail(String userEmail);
    OwnerResponseDTO findOwnerByCode(String ownerCode);
    OwnerRequestDTO  showAllDataOfOwnerById(long id);
    String addNewOwner(OwnerRequestDTO newOwnerDTO);
    String updateOwner(long ownerId , OwnerRequestDTO updatedOwnerDTO);
    String deleteOwnerById(long id);

}
