package br.com.devcapu.domain.useCase

import br.com.devcapu.domain.model.Patient
import br.com.devcapu.domain.repository.PatientRepository

class SavePatient(private val patientRepository: PatientRepository) {
    operator fun invoke(patient: Patient) {
        val calculateBMI = CalculateBMI()
        patient.bmi = calculateBMI(patient)

        patientRepository.save(patient)
    }
}