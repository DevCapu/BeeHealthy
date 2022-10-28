package br.com.devcapu.beehealthy.common.domain.model

import br.com.devcapu.beehealthy.common.domain.model.patient.Email
import br.com.devcapu.beehealthy.common.domain.model.patient.health.ActivityLevel
import br.com.devcapu.beehealthy.common.domain.model.patient.health.BiologicalGender
import br.com.devcapu.beehealthy.common.domain.model.patient.health.BodyCaloriesNeeds
import br.com.devcapu.beehealthy.common.domain.model.patient.health.Objective
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
    val bodyCaloriesNeeds: BodyCaloriesNeeds?,
) : Serializable {
    val hasAValidEmail = email.isValid

    val idealWeight: Float
        get() = when (biologicGender) {
            BiologicalGender.MALE -> ((height * 100) - 100) * 0.9f
            BiologicalGender.FEMALE ->((height * 100) - 100) * 0.85f
        }
}
