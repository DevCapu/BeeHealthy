package br.com.devcapu.beehealthy.app.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devcapu.beehealthy.domain.model.BiologicalGender
import br.com.devcapu.beehealthy.domain.model.Patient

@Entity(tableName = "patient")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val age: Int,
    val weight: Float,
    val height: Float,
    val biologicGender: BiologicalGender,
    val activityLevel: String,
    val objective: String,
) {
    companion object {
        fun from(patient: Patient): PatientEntity {
            return PatientEntity(
                name = patient.name,
                email = patient.email.toString(),
                age = patient.age,
                weight = patient.weight,
                height = patient.height,
                biologicGender = patient.biologicGender,
                activityLevel = patient.activityLevel,
                objective = patient.objective,
            )
        }
    }
}