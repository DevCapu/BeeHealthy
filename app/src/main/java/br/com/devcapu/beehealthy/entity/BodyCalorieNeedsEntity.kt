package br.com.devcapu.beehealthy.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devcapu.beehealthy.model.patient.health.BodyCaloriesNeeds

@Entity(tableName = "bodyCalorieNeeds")
class BodyCalorieNeedsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val patientId: Long = 0,
    val bmi: Double,
    val basalEnergyExpenditure: Double,
    val totalEnergyExpenditure: Double,
    val caloriesToCommitObjective: Double,
    val carbohydrate: Int,
    val protein: Int,
    val fats: Int,
) {
    companion object {
        fun from(caloriesNeeds: BodyCaloriesNeeds, patientId: Long) = BodyCalorieNeedsEntity(
            patientId = patientId,
            bmi = caloriesNeeds.bmi.value,
            basalEnergyExpenditure = caloriesNeeds.basalEnergyExpenditure.value,
            totalEnergyExpenditure = caloriesNeeds.totalEnergyExpenditure.value,
            caloriesToCommitObjective = caloriesNeeds.caloriesToCommitObjective.value,
            carbohydrate = caloriesNeeds.macros.carbohydrate,
            protein = caloriesNeeds.macros.protein,
            fats = caloriesNeeds.macros.fats,
        )

        fun from(caloriesNeeds: BodyCaloriesNeeds) = BodyCalorieNeedsEntity(
            bmi = caloriesNeeds.bmi.value,
            basalEnergyExpenditure = caloriesNeeds.basalEnergyExpenditure.value,
            totalEnergyExpenditure = caloriesNeeds.totalEnergyExpenditure.value,
            caloriesToCommitObjective = caloriesNeeds.caloriesToCommitObjective.value,
            carbohydrate = caloriesNeeds.macros.carbohydrate,
            protein = caloriesNeeds.macros.protein,
            fats = caloriesNeeds.macros.fats
        )
    }
}