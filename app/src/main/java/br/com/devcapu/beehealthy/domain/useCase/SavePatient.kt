package br.com.devcapu.beehealthy.domain.useCase

import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.model.patient.health.*
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository

class SavePatient(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
) {
    suspend operator fun invoke(patient: Patient) {
        patientRepository.save(patient) { id ->
            val bodyCaloriesNeeds = calculateHealthInfo(patient)
            healthRepository.save(bodyCaloriesNeeds, id)
        }
    }

    private fun calculateHealthInfo(patient: Patient): BodyCaloriesNeeds {
        val bmi = BMI.calculate(patient)
        val basalEnergyExpenditure = BasalEnergyExpenditure.calculate(patient)
        val totalEnergyExpenditure = TotalEnergyExpenditure.calculate(basalEnergyExpenditure, patient.activityLevel)
        val caloriesToCommitObjective = CaloriesToCommitObjective.calculate(totalEnergyExpenditure, patient.objective)

        return BodyCaloriesNeeds(
            bmi,
            basalEnergyExpenditure,
            totalEnergyExpenditure,
            caloriesToCommitObjective
        )
    }
}