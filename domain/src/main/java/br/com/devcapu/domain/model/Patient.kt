package br.com.devcapu.domain.model

import java.io.Serializable

data class Patient(
    val name: String,
    val email: String,
    val age: String,
    val weight: Float,
    val height: Float,
    val biologicGender: String,
    val activityLevel: String,
    val objective: String
): Serializable {
    var bmi = 0.0f
}
