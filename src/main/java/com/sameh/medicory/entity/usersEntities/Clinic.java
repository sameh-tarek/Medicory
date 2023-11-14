package com.sameh.medicory.entity.usersEntities;

import com.sameh.medicory.entity.usersEntities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String googleMapsLink;

    @Column(nullable = false)
    private String address;

    private String ownerName;

    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
