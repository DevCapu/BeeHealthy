package br.com.devcapu.beehealthy.common.domain.model.patient.health

enum class Objective(val calories: Double) {
    LOSE(-400.00),
    MAINTAIN(0.0),
    GAIN(300.0)
}