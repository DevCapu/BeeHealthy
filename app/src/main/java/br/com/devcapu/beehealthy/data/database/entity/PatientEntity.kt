package br.com.devcapu.beehealthy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devcapu.domain.model.Patient

@Entity(tableName = "patient")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val age: String,
    val weight: Float,
    val height: Float,
    val biologicGender: String,
    val activityLevel: String,
    val objective: String,
    val bmi: Float,
) {

    companion object {
        fun from(patient: Patient): PatientEntity {
            return PatientEntity(
                name = patient.name,
                email = patient.email,
                age = patient.age,
                weight = patient.weight,
                height = patient.height,
                biologicGender = patient.biologicGender,
                activityLevel = patient.activityLevel,
                objective = patient.objective,
                bmi = patient.bmi
            )
        }
    }
}