package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.domain.model.Patient

class PatientRepository(private val patientDataSource: PatientDataSource) {

    fun save(patient: Patient): Long = patientDataSource.save(patient)
}