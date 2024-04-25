package com.sameh.medicory.service.admin.users;


import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import com.sameh.medicory.model.users.owner.OwnerResponseDTO;

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
