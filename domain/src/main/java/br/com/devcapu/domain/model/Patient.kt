package br.com.devcapu.domain.model

import java.io.Serializable

data class Patient(
    val name: String,
    val email: String,
    val age: Int,
    val weight: Float,
    val height: Float,
    val biologicGender: BiologicalGender,
    val activityLevel: String,
    val objective: String
): Serializable
