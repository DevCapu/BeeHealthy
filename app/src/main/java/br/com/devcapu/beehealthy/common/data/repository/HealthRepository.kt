package br.com.devcapu.beehealthy.common.data.repository

import br.com.devcapu.beehealthy.common.data.datasource.HealthResultDAO
import br.com.devcapu.beehealthy.common.data.model.BodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.common.domain.model.patient.health.BodyCaloriesNeeds

class HealthRepository(private val dao: HealthResultDAO) {
    fun save(health: BodyCaloriesNeeds, patientId: Long) {
        val entity = BodyCalorieNeedsEntity.from(health, patientId)
        dao.insert(entity = entity)
    }
}