package br.com.devcapu.beehealthy.data.database.dataSource

import android.content.Context
import br.com.devcapu.beehealthy.data.database.BeeHealthyDatabase
import br.com.devcapu.domain.repository.PatientDataSource

class PatientDataSource(context: Context): PatientDataSource {
    private val patientDao = BeeHealthyDatabase.getInstance(context = context).patientDao()

    override fun saveBMI(bmi: Float) = patientDao.saveBMI(bmi = bmi)
}