package com.sameh.medicory.entity.medicationEntities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "voice_record")
public class VoiceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String path;
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;
}
