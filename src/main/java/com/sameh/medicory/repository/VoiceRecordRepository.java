package com.sameh.medicory.repository;

import com.sameh.medicory.entity.medicationEntities.VoiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceRecordRepository extends JpaRepository<VoiceRecord,Long> {
}
