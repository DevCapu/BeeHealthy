package br.com.devcapu.beehealthy.domain.useCase.healthCalculator

import br.com.devcapu.beehealthy.domain.model.BiologicalGender.FEMALE
import br.com.devcapu.beehealthy.domain.model.BiologicalGender.MALE
import br.com.devcapu.beehealthy.domain.model.Patient

class CalculateBasalEnergyExpenditure {
    operator fun invoke(patient: Patient) = when (patient.biologicGender) {
        MALE -> {
            MALE.coefficient1 +
                    (patient.weight.times(MALE.coefficient2)) +
                    (patient.height.times(MALE.coefficient3)) -
                    (patient.age.times(MALE.coefficient4))
        }
        FEMALE -> {
            FEMALE.coefficient1 +
                    (patient.weight.times(FEMALE.coefficient2)) +
                    (patient.height.times(FEMALE.coefficient3)) -
                    (patient.age.times(FEMALE.coefficient4))
        }
    }
}