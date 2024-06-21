package com.graduationProject.medicory.repository.otherRepositories;

import com.graduationProject.medicory.entity.medicationEntities.VoiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoiceRecordRepository extends JpaRepository<VoiceRecord,Long> {

    //@Query("FROM VoiceRecord where id = :voidRecordId AND medication.id = :medicationId AND medication.owner.user = :userCode")
    @Query("SELECT vr FROM VoiceRecord vr " +
            "JOIN vr.medication m " +
            "JOIN m.owner o " +
            "JOIN o.user u " +
            "WHERE vr.id = :voidRecordId " +
            "AND m.id = :medicationId " +
            "AND u.code = :userCode")
    Optional<VoiceRecord> findByUserCodeAndMedicationIdAndRecordId(String userCode, Long medicationId,Long voidRecordId);
}
