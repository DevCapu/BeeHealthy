package br.com.devcapu.domain.repository

import br.com.devcapu.domain.model.Patient

class PatientRepository(private val patientDataSource: PatientDataSource) {

    fun save(patient: Patient) = patientDataSource.save(patient)
}