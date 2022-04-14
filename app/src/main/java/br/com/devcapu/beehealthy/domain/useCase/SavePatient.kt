package br.com.devcapu.beehealthy.domain.useCase

import br.com.devcapu.beehealthy.domain.model.HealthResult
import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository
import br.com.devcapu.beehealthy.domain.useCase.healthCalculator.CalculateBasalEnergyExpenditure

class SavePatient(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
) {
    operator fun invoke(patient: Patient) {
        val healthResult = calculateHealthInfo(patient)
        val patientId = patientRepository.save(patient)
        healthRepository.save(healthResult, patientId)
    }

    private fun calculateHealthInfo(patient: Patient): HealthResult {
        val calculateBasalEnergyExpenditure = CalculateBasalEnergyExpenditure()
        val basalEnergyExpenditure = calculateBasalEnergyExpenditure(patient)

        val calculateBMI = CalculateBMI()
        val bmi = calculateBMI(patient)

        return HealthResult(bmi, basalEnergyExpenditure)
    }
}