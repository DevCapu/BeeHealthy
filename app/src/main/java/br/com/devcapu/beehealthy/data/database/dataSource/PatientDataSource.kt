package br.com.devcapu.beehealthy.data.database.dataSource

import android.content.Context
import br.com.devcapu.beehealthy.data.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.data.database.entity.PatientEntity
import br.com.devcapu.domain.model.Patient
import br.com.devcapu.domain.repository.PatientDataSource

class PatientDataSource(context: Context): PatientDataSource {
    private val patientDao = BeeHealthyDatabase.getInstance(context = context).patientDao()

    override fun save(patient: Patient): Long {
        return patientDao.insert(PatientEntity.from(patient))
    }
}