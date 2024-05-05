package com.graduationProject.medicory.entity.usersEntities;

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

    @Column(name="google_maps_link")
    private String googleMapsLink;

    @Column(nullable = false)
    private String address;

    @Column(name="owner_name")
    private String ownerName;

    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}