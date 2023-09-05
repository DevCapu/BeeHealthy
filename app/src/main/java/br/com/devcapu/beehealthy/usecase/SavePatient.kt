package br.com.devcapu.beehealthy.usecase

import br.com.devcapu.auth.repository.RegisterRepository
import br.com.devcapu.beehealthy.repository.HealthRepository
import br.com.devcapu.beehealthy.repository.PatientRepository
import br.com.devcapu.beehealthy.model.nutrition.Macros
import br.com.devcapu.beehealthy.model.patient.Patient
import br.com.devcapu.beehealthy.model.patient.health.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavePatient(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
    private val registerRepository: br.com.devcapu.auth.repository.RegisterRepository,
) {
    operator fun invoke(
        patient: Patient,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
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
        val totalEnergyExpenditure =
            TotalEnergyExpenditure.calculate(basalEnergyExpenditure, patient.activityLevel)
        val caloriesToCommitObjective =
            CaloriesToCommitObjective.calculate(totalEnergyExpenditure, patient.objective)
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