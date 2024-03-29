package br.com.devcapu.beehealthy.model.patient.health

enum class ActivityLevel(val coefficient: Double) {
    SEDENTARY(1.2),
    LOW(1.375),
    MODERATE(1.55),
    ACTIVE(1.75)
}