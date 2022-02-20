package br.com.devcapu.beehealthy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import br.com.devcapu.domain.model.HealthResult

@Entity(tableName = "healthResult")
class HealthResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val patientId: Long,
    val bmi: Double,
    val basalEnergyExpenditure: Double,
) {
    companion object {
        fun from(healthResult: HealthResult, patientId: Long) = HealthResultEntity(
            patientId = patientId,
            bmi = healthResult.bmi,
            basalEnergyExpenditure = healthResult.basalEnergyExpenditure
        )
    }
}