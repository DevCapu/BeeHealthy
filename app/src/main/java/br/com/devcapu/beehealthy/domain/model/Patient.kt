package br.com.devcapu.beehealthy.domain.model

import br.com.devcapu.beehealthy.domain.model.patient.*
import br.com.devcapu.beehealthy.domain.model.patient.health.ActivityLevel
import br.com.devcapu.beehealthy.domain.model.patient.health.BiologicalGender
import br.com.devcapu.beehealthy.domain.model.patient.health.BodyCaloriesNeeds
import br.com.devcapu.beehealthy.domain.model.patient.health.Objective
import java.io.Serializable

data class Patient(
    val name: String,
    val email: Email,
    val age: Int,
    val weight: Float,
    val height: Float,
    val biologicGender: BiologicalGender,
    val activityLevel: ActivityLevel,
    val objective: Objective,
    val bodyCaloriesNeeds: BodyCaloriesNeeds?
): Serializable {
    val hasAValidEmail = email.isValid
}
