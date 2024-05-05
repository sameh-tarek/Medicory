package com.graduationProject.medicory.entity.phoneEntities;

import com.graduationProject.medicory.entity.usersEntities.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "relative_phone_numbers")
public class RelativePhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String relation;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}

