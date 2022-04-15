package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.app.database.dao.PatientDAO
import br.com.devcapu.beehealthy.app.database.entity.PatientEntity
import br.com.devcapu.beehealthy.domain.model.Patient

class PatientRepository(private val patientDAO: PatientDAO) {

    fun save(patient: Patient, onComplete: (Long) -> Unit) {
        val id = patientDAO.insert(PatientEntity.from(patient))
        onComplete(id)
    }
}