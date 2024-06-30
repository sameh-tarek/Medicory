package com.graduationProject.medicory.model.VoiceRecord;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class VoiceRecordResponse {
    private Long id;
    private String path;
    private LocalDateTime createdAt;
}
