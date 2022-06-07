package br.com.devcapu.beehealthy.domain.model.patient

class TotalEnergyExpenditure {
    companion object {
        fun calculate(basalEnergyExpenditure: Double, activityLevel: ActivityLevel) =
            basalEnergyExpenditure * activityLevel.coefficient
    }
}