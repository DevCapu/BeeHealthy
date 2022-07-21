package br.com.devcapu.beehealthy.app.database.entity

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
