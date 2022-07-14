package br.com.devcapu.beehealthy.domain.model.patient.health

data class BodyCaloriesNeeds(
    val bmi: BMI,
    val basalEnergyExpenditure: BasalEnergyExpenditure,
    val totalEnergyExpenditure: TotalEnergyExpenditure,
    val caloriesToCommitObjective: CaloriesToCommitObjective
)