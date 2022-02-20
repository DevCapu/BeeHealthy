package br.com.devcapu.domain.repository

import br.com.devcapu.domain.model.HealthResult

class HealthRepository(private val healthDataSource: HealthDataSource) {
    fun save(health: HealthResult, patientId: Long) = healthDataSource.save(health, patientId)
}