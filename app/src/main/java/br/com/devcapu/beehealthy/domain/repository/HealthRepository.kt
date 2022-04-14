package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.domain.model.HealthResult

class HealthRepository(private val healthDataSource: HealthDataSource) {
    fun save(health: HealthResult, patientId: Long) = healthDataSource.save(health, patientId)
}