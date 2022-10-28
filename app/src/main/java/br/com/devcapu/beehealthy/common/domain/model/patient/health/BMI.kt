package br.com.devcapu.beehealthy.common.domain.model.patient.health

import br.com.devcapu.beehealthy.common.domain.model.Patient
import kotlin.math.pow

data class BMI(val value: Double) {
    companion object {
        fun calculate(patient: Patient): BMI {
            val result = patient.weight.div(patient.height.pow(2)).toDouble()
            return BMI(value = result)
        }
    }
}