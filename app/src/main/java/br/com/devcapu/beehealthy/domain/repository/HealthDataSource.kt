package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.domain.model.HealthResult

interface HealthDataSource {

    fun save(health: HealthResult, patientId: Long)
}
