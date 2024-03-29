package br.com.devcapu.beehealthy.common.domain.model.patient

import br.com.devcapu.beehealthy.model.patient.health.ActivityLevel.*
import br.com.devcapu.beehealthy.model.patient.health.BasalEnergyExpenditure
import br.com.devcapu.beehealthy.model.patient.health.TotalEnergyExpenditure
import org.junit.Assert
import org.junit.Test

class TotalEnergyExpenditureTest {

    @Test
    fun `should calculate correctly when calculate total energy expenditure with sedentary activity level`() {
        val totalEnergyExpenditure = TotalEnergyExpenditure.calculate(
            basalEnergyExpenditure = BasalEnergyExpenditure(value = 1500.00),
            activityLevel = SEDENTARY
        )

        Assert.assertEquals(1800.0, totalEnergyExpenditure.value, 0.1)
    }

    @Test
    fun `should calculate correctly when calculate total energy expenditure with low activity level`() {
        val totalEnergyExpenditure = TotalEnergyExpenditure.calculate(
            basalEnergyExpenditure = BasalEnergyExpenditure(value = 1500.00),
            activityLevel = LOW
        )

        Assert.assertEquals(2062.5, totalEnergyExpenditure.value, 0.1)
    }

    @Test
    fun `should calculate correctly when calculate total energy expenditure with moderate activity level`() {
        val totalEnergyExpenditure = TotalEnergyExpenditure.calculate(
            basalEnergyExpenditure = BasalEnergyExpenditure(value = 1500.00),
            activityLevel = MODERATE
        )

        Assert.assertEquals(2325.0, totalEnergyExpenditure.value, 0.1)
    }

    @Test
    fun `should calculate correctly when calculate total energy expenditure with active activity level`() {
        val totalEnergyExpenditure = TotalEnergyExpenditure.calculate(
            basalEnergyExpenditure = BasalEnergyExpenditure(value = 1500.00),
            activityLevel = ACTIVE
        )

        Assert.assertEquals(2625.0, totalEnergyExpenditure.value, 0.1)
    }
}