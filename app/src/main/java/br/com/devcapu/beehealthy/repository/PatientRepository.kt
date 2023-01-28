package br.com.devcapu.beehealthy.repository

import br.com.devcapu.beehealthy.dao.PatientDAO
import br.com.devcapu.beehealthy.entity.BodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.entity.PatientAndBodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.entity.PatientEntity
import br.com.devcapu.beehealthy.model.nutrition.Macros
import br.com.devcapu.beehealthy.model.patient.Email
import br.com.devcapu.beehealthy.model.patient.Patient
import br.com.devcapu.beehealthy.model.patient.health.*

class PatientRepository(private val patientDAO: PatientDAO) {

    fun save(patient: Patient, onComplete: (Long) -> Unit) {
        if (patient.hasAValidEmail) {
            val id = patientDAO.insert(PatientEntity.from(patient))
            onComplete(id)
        }
    }

    suspend fun searchUserWith(email: String): Patient {
        val entity = patientDAO.findWithEmail(email)
        return map(entity)
    }

    private fun map(patientAndBodyCalorieNeedsEntity: PatientAndBodyCalorieNeedsEntity): Patient {
        val patientEntity = patientAndBodyCalorieNeedsEntity.patientEntity
        val bodyCalorieNeedsEntity = patientAndBodyCalorieNeedsEntity.bodyCalorieNeedsEntity
        return Patient(
            name = patientEntity.name,
            email = Email(patientEntity.email),
            age = patientEntity.age,
            weight = patientEntity.weight,
            height = patientEntity.height,
            biologicGender = patientEntity.biologicGender,
            activityLevel = ActivityLevel.valueOf(patientEntity.activityLevel),
            objective = Objective.valueOf(patientEntity.objective),
            bodyCaloriesNeeds = map(bodyCalorieNeedsEntity)
        )
    }

    private fun map(entity: BodyCalorieNeedsEntity) = BodyCaloriesNeeds(
        bmi = BMI(value = entity.bmi),
        basalEnergyExpenditure = BasalEnergyExpenditure(value = entity.basalEnergyExpenditure),
        totalEnergyExpenditure = TotalEnergyExpenditure(value = entity.totalEnergyExpenditure),
        caloriesToCommitObjective = CaloriesToCommitObjective(value = entity.caloriesToCommitObjective),
        macros = Macros(
            carbohydrate = entity.carbohydrate,
            protein = entity.protein,
            fats = entity.fats
        )
    )
}