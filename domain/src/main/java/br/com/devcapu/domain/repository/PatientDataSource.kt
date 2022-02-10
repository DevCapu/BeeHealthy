package br.com.devcapu.domain.repository

import br.com.devcapu.domain.model.Patient

interface PatientDataSource {
    fun save(patient: Patient)
}