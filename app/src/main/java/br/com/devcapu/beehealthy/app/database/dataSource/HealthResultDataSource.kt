package br.com.devcapu.beehealthy.app.database.dataSource

import android.content.Context
import br.com.devcapu.beehealthy.app.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.app.database.entity.HealthResultEntity
import br.com.devcapu.beehealthy.domain.model.HealthResult
import br.com.devcapu.beehealthy.domain.repository.HealthDataSource

class HealthResultDataSource(context: Context) : HealthDataSource {
    private val patientDao = BeeHealthyDatabase.getInstance(context = context).healthResultDao()
    override fun save(health: HealthResult, patientId: Long) {
        patientDao.insert(HealthResultEntity.from(healthResult = health, patientId = patientId))
    }
}