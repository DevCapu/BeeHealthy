package br.com.devcapu.beehealthy.model.patient.health

import br.com.devcapu.beehealthy.model.patient.health.Objective.*

class CaloriesToCommitObjective(val value: Double) {

    companion object {
        fun calculate(
            totalEnergyExpenditure: TotalEnergyExpenditure,
            objective: Objective,
        ): CaloriesToCommitObjective {
            val result = when (objective) {
                LOSE -> totalEnergyExpenditure.value + LOSE.calories
                MAINTAIN -> totalEnergyExpenditure.value + MAINTAIN.calories
                GAIN -> totalEnergyExpenditure.value + GAIN.calories
            }

            return CaloriesToCommitObjective(value = result)
        }
    }
}