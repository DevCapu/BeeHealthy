package br.com.devcapu.beehealthy.domain.useCase

import br.com.devcapu.beehealthy.domain.model.Patient
import kotlin.math.pow

class CalculateBMI {
    operator fun invoke(patient: Patient) = patient.weight.div(patient.height.pow(2)).toDouble()
}