package br.com.devcapu.domain.repository

import br.com.devcapu.domain.model.HealthResult

interface HealthDataSource {

    fun save(health: HealthResult, patientId: Long)
}
