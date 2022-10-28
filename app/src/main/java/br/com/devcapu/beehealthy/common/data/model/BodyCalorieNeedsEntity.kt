package br.com.devcapu.beehealthy.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devcapu.beehealthy.common.domain.model.patient.health.BodyCaloriesNeeds

@Entity(tableName = "bodyCalorieNeeds")
class BodyCalorieNeedsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val patientId: Long = 0,
    val bmi: Double,
    val basalEnergyExpenditure: Double,
    val totalEnergyExpenditure: Double,
    val caloriesToCommitObjective: Double,
) {
    companion object {
        fun from(caloriesNeeds: BodyCaloriesNeeds, patientId: Long) = BodyCalorieNeedsEntity(
            patientId = patientId,
            bmi = caloriesNeeds.bmi.value,
            basalEnergyExpenditure = caloriesNeeds.basalEnergyExpenditure.value,
            totalEnergyExpenditure = caloriesNeeds.totalEnergyExpenditure.value,
            caloriesToCommitObjective = caloriesNeeds.caloriesToCommitObjective.value
        )

        fun from(caloriesNeeds: BodyCaloriesNeeds) = BodyCalorieNeedsEntity(
            bmi = caloriesNeeds.bmi.value,
            basalEnergyExpenditure = caloriesNeeds.basalEnergyExpenditure.value,
            totalEnergyExpenditure = caloriesNeeds.totalEnergyExpenditure.value,
            caloriesToCommitObjective = caloriesNeeds.caloriesToCommitObjective.value
        )
    }
}