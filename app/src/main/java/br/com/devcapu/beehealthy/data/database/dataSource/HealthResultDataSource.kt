package br.com.devcapu.beehealthy.data.database.dataSource

import android.content.Context
import br.com.devcapu.beehealthy.data.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.data.database.entity.HealthResultEntity
import br.com.devcapu.domain.model.HealthResult
import br.com.devcapu.domain.repository.HealthDataSource

class HealthResultDataSource(context: Context) : HealthDataSource {
    private val patientDao = BeeHealthyDatabase.getInstance(context = context).healthResultDao()
    override fun save(health: HealthResult, patientId: Long) {
        patientDao.insert(HealthResultEntity.from(healthResult = health, patientId = patientId))
    }
}