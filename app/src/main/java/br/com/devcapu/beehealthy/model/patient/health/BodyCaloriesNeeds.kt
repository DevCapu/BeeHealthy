package br.com.devcapu.beehealthy.model.patient.health

import br.com.devcapu.beehealthy.model.nutrition.Macros

data class BodyCaloriesNeeds(
    val bmi: BMI,
    val basalEnergyExpenditure: BasalEnergyExpenditure,
    val totalEnergyExpenditure: TotalEnergyExpenditure,
    val caloriesToCommitObjective: CaloriesToCommitObjective,
    val macros: Macros,
)