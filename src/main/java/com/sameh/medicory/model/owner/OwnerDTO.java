package com.sameh.medicory.model.owner;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OwnerDTO {

    private String name;
    private Integer age;
    private String gender;
    private List<String> phoneNumbers;
    private List<String> relativePhoneNumbers;
    private String Email;
    private String address;
    private String bloodType;
    private long nationalId;
    private String maritalStatus;
    private String job;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
