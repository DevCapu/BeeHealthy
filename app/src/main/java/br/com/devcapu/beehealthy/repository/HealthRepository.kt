package br.com.devcapu.beehealthy.repository

import br.com.devcapu.beehealthy.dao.HealthResultDAO
import br.com.devcapu.beehealthy.entity.BodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.model.patient.health.BodyCaloriesNeeds

class HealthRepository(private val dao: HealthResultDAO) {
    fun save(health: BodyCaloriesNeeds, patientId: Long) {
        val entity = BodyCalorieNeedsEntity.from(health, patientId)
        dao.insert(entity = entity)
    }
}