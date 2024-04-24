package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.medicationEntities.CurrentSchedule;
import com.sameh.medicory.mapper.CurrentScheduleMapper;
import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class CurrentScheduleMapperImpl implements CurrentScheduleMapper {
    @Override
    public CurrentSchedule toEntity(CurrentScheduleRequest currentScheduleRequest) {
        return CurrentSchedule.builder()
                .owner(currentScheduleRequest.getOwner())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
