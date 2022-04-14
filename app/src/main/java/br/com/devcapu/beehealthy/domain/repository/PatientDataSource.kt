package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.domain.model.Patient

interface PatientDataSource {
    fun save(patient: Patient): Long
}