package br.com.devcapu.beehealthy.register.domain.useCase

import br.com.devcapu.beehealthy.common.domain.model.patient.Patient
import br.com.devcapu.beehealthy.common.domain.model.patient.health.*
import br.com.devcapu.beehealthy.common.data.repository.HealthRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.common.domain.model.nutrition.Macros
import br.com.devcapu.beehealthy.register.data.RegisterRepository
import kotlinx.coroutines.*

class SavePatient(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
    private val registerRepository: RegisterRepository,
) {
     operator fun invoke(
         patient: Patient,
         password: String,
         onSuccess: () -> Unit,
         onFailure: (Exception) -> Unit
    ) {
        registerRepository.register(
            email = patient.email.address,
            password = password,
            onSuccess = {
                CoroutineScope(Dispatchers.IO).launch {
                    patientRepository.save(patient) { id ->
                        val bodyCaloriesNeeds = calculateHealthInfo(patient)
                        healthRepository.save(bodyCaloriesNeeds, id)
                    }
                    onSuccess()
                }
            },
            onFailure = { onFailure(it) }
        )
    }

    private fun calculateHealthInfo(patient: Patient): BodyCaloriesNeeds {
        val bmi = BMI.calculate(patient)
        val basalEnergyExpenditure = BasalEnergyExpenditure.calculate(patient)
        val totalEnergyExpenditure = TotalEnergyExpenditure.calculate(basalEnergyExpenditure, patient.activityLevel)
        val caloriesToCommitObjective = CaloriesToCommitObjective.calculate(totalEnergyExpenditure, patient.objective)
        val macros = Macros.calculate(caloriesToCommitObjective)

        return BodyCaloriesNeeds(
            bmi = bmi,
            basalEnergyExpenditure = basalEnergyExpenditure,
            totalEnergyExpenditure = totalEnergyExpenditure,
            caloriesToCommitObjective = caloriesToCommitObjective,
            macros = macros
        )
    }
}