package br.com.devcapu.beehealthy.app.database.dataSource

import android.content.Context
import br.com.devcapu.beehealthy.app.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.app.database.entity.PatientEntity
import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.repository.PatientDataSource

class PatientDataSource(context: Context): PatientDataSource {
    private val patientDao = BeeHealthyDatabase.getInstance(context = context).patientDao()

    override fun save(patient: Patient): Long {
        return patientDao.insert(PatientEntity.from(patient))
    }
}