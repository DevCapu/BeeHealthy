package br.com.devcapu.beehealthy.domain.model

import br.com.devcapu.beehealthy.domain.model.patient.Email
import java.io.Serializable

data class Patient(
    val name: String,
    val email: Email,
    val age: Int,
    val weight: Float,
    val height: Float,
    val biologicGender: BiologicalGender,
    val activityLevel: String,
    val objective: String
): Serializable
