package br.com.devcapu.beehealthy.data.database.entity

import androidx.room.Entity

@Entity
data class PatientEntity(
    private val height: Float,
    private val weight: Float
)