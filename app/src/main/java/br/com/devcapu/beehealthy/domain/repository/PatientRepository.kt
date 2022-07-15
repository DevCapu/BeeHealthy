package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.app.database.dao.PatientDAO
import br.com.devcapu.beehealthy.app.database.entity.PatientEntity
import br.com.devcapu.beehealthy.domain.model.Patient

class PatientRepository(private val patientDAO: PatientDAO) {

    suspend fun save(patient: Patient, onComplete: suspend (Long) -> Unit) {
        if (patient.hasAValidEmail) {
            val id = patientDAO.insert(PatientEntity.from(patient))
            onComplete(id)
        }
    }
}