package com.sameh.medicory.model.users;

import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelativePhoneNumberDTO {

    private Long id ;

    private String phone;

    private String relation;
}
