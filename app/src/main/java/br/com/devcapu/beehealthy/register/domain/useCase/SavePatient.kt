package br.com.devcapu.beehealthy.register.domain.useCase

import br.com.devcapu.beehealthy.common.domain.model.Patient
import br.com.devcapu.beehealthy.common.domain.model.patient.health.*
import br.com.devcapu.beehealthy.common.data.repository.HealthRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.register.data.RegisterRepository

class SavePatient(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
    private val registerRepository: RegisterRepository,
) {
     operator fun invoke(
        patient: Patient,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        registerRepository.register(
            email = patient.email.address,
            password = "",
            onSuccess = {
                patientRepository.save(patient) { id ->
                    val bodyCaloriesNeeds = calculateHealthInfo(patient)
                    healthRepository.save(bodyCaloriesNeeds, id)
                }
                onSuccess()
            },
            onFailure = { onFailure(it) }
        )
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