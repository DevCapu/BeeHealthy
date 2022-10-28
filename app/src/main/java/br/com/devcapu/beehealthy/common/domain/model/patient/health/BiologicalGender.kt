package br.com.devcapu.beehealthy.common.domain.model.patient.health

enum class BiologicalGender(
    val coefficient1: Double,
    val coefficient2: Double,
    val coefficient3: Double,
    val coefficient4: Double,
) {
    MALE(66.5, 13.75, 5.003, 6.775),
    FEMALE(665.1, 9.563, 1.850, 4.676)
}