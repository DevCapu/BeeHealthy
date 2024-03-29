package br.com.devcapu.beehealthy.model.patient

import br.com.devcapu.beehealthy.model.patient.health.ActivityLevel
import br.com.devcapu.beehealthy.model.patient.health.BiologicalGender
import br.com.devcapu.beehealthy.model.patient.health.BiologicalGender.FEMALE
import br.com.devcapu.beehealthy.model.patient.health.BiologicalGender.MALE
import br.com.devcapu.beehealthy.model.patient.health.BodyCaloriesNeeds
import br.com.devcapu.beehealthy.model.patient.health.Objective
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
            MALE -> ((height * 100) - 100) * 0.9f
            FEMALE -> ((height * 100) - 100) * 0.85f
        }
}
