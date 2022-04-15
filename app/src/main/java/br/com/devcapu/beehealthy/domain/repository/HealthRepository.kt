package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.app.database.dao.HealthResultDAO
import br.com.devcapu.beehealthy.app.database.entity.HealthResultEntity
import br.com.devcapu.beehealthy.domain.model.HealthResult

class HealthRepository(private val dao: HealthResultDAO) {
    fun save(health: HealthResult, patientId: Long) {
        val entity = HealthResultEntity.from(health, patientId)
        dao.insert(entity = entity)
    }
}