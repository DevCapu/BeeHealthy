package br.com.devcapu.beehealthy.common.domain.model.patient.health

data class TotalEnergyExpenditure(
    val value: Double,
) {
    companion object {
        fun calculate(
            basalEnergyExpenditure: BasalEnergyExpenditure,
            activityLevel: ActivityLevel,
        ): TotalEnergyExpenditure {
            val result = basalEnergyExpenditure.value * activityLevel.coefficient
            return TotalEnergyExpenditure(value = result)
        }
    }
}