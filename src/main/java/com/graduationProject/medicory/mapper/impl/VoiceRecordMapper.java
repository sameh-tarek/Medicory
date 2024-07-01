package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.medicationEntities.VoiceRecord;
import com.graduationProject.medicory.model.VoiceRecord.VoiceRecordResponse;
import org.springframework.stereotype.Component;

@Component
public class VoiceRecordMapper {

    public VoiceRecordResponse toDTO(VoiceRecord voiceRecord) {
        if (voiceRecord==null){return null;}

        VoiceRecordResponse voiceRecordResponse =
                VoiceRecordResponse.builder()
                        .id(voiceRecord.getId())
                        .path(voiceRecord.getPath())
                        .createdAt(voiceRecord.getCreatedAt()).build();

        return voiceRecordResponse;
    }
}
