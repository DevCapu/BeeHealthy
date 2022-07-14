package br.com.devcapu.beehealthy.domain.model.patient.health

import br.com.devcapu.beehealthy.domain.model.Patient

data class BasalEnergyExpenditure(val value: Double) {

    companion object {
        fun calculate(patient: Patient): BasalEnergyExpenditure {
            val result = when (patient.biologicGender) {
                BiologicalGender.MALE -> BiologicalGender.MALE.coefficient1 +
                        (patient.weight.times(BiologicalGender.MALE.coefficient2)) +
                        (patient.height.times(BiologicalGender.MALE.coefficient3)) -
                        (patient.age.times(BiologicalGender.MALE.coefficient4))
                BiologicalGender.FEMALE -> BiologicalGender.FEMALE.coefficient1 +
                        (patient.weight.times(BiologicalGender.FEMALE.coefficient2)) +
                        (patient.height.times(BiologicalGender.FEMALE.coefficient3)) -
                        (patient.age.times(BiologicalGender.FEMALE.coefficient4))

            }

            return BasalEnergyExpenditure(value = result)
        }
    }
}