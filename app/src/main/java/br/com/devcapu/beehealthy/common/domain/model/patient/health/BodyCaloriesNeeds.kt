package br.com.devcapu.beehealthy.common.domain.model.patient.health

import br.com.devcapu.beehealthy.common.domain.model.nutrition.Macros

data class BodyCaloriesNeeds(
    val bmi: BMI,
    val basalEnergyExpenditure: BasalEnergyExpenditure,
    val totalEnergyExpenditure: TotalEnergyExpenditure,
    val caloriesToCommitObjective: CaloriesToCommitObjective,
    val macros: Macros
)