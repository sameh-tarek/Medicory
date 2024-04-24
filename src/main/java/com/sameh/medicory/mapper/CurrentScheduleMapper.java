package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.medicationEntities.CurrentSchedule;
import com.sameh.medicory.model.medication.CurrentScheduleRequest;

public interface CurrentScheduleMapper {
    CurrentSchedule toEntity(CurrentScheduleRequest currentScheduleRequest);
}
