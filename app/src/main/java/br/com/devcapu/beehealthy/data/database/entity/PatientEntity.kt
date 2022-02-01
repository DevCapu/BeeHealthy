package br.com.devcapu.beehealthy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val bmi: Float,
)