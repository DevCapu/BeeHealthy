package br.com.devcapu.domain.useCase

import br.com.devcapu.domain.model.Patient
import kotlin.math.pow

class CalculateBMI {
    operator fun invoke(patient: Patient) = patient.weight.times(patient.height.pow(2))
}