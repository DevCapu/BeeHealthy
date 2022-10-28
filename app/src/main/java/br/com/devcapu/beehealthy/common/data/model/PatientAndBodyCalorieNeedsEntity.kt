package br.com.devcapu.beehealthy.common.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class PatientAndBodyCalorieNeedsEntity(
    @Embedded val patientEntity: PatientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "patientId"
    )
    val bodyCalorieNeedsEntity: BodyCalorieNeedsEntity
)
