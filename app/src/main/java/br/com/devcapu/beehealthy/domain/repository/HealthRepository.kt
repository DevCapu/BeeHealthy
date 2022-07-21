package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.app.database.dao.HealthResultDAO
import br.com.devcapu.beehealthy.app.database.entity.BodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.domain.model.patient.health.*

class HealthRepository(private val dao: HealthResultDAO) {
    suspend fun save(health: BodyCaloriesNeeds, patientId: Long) {
        val entity = BodyCalorieNeedsEntity.from(health, patientId)
        dao.insert(entity = entity)
    }
}