package br.com.devcapu.beehealthy.domain.repository

import br.com.devcapu.beehealthy.app.database.dao.PatientDAO
import br.com.devcapu.beehealthy.app.database.entity.PatientEntity
import br.com.devcapu.beehealthy.domain.model.patient.health.BiologicalGender
import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.model.patient.Email
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PatientRepositoryTest {
    lateinit var repository: PatientRepository
    @MockK
    lateinit var patient: Patient
    @MockK
    lateinit var patientDAO: PatientDAO
    @MockK
    lateinit var patientEntity: PatientEntity

    @Before
    fun setUp()  {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = PatientRepository(patientDAO)
    }

    @Test
    fun `should call DAO insert when email is valid`() = runBlocking {
        every { patient.hasAValidEmail }.returns(true)
        every { patient.name }.returns("Jhon Doe")
        every { patient.email }.returns(Email(""))
        every { patient.age }.returns(23)
        every { patient.weight }.returns(80.3f)
        every { patient.height }.returns(1.83f)
        every { patient.biologicGender }.returns(BiologicalGender.MALE)
        every { patient.activityLevel }.returns("SEDENTARY")
        every { patient.objective }.returns("LOSE")
        every { PatientEntity.from(patient) } returns mockk()
        coEvery { patientDAO.insert(any()) } returns mockk()

        repository.save(patient = patient) { }

        coVerify { patientDAO.insert(any()) }

//        name = "Jhon Doe",
//        email = Email("jhon.doe@gmail.com"),
//        age = 21,
//        weight = 80.0.toFloat(),
//        height = 1.83.toFloat(),
//        biologicGender = BiologicalGender.MALE,
//        activityLevel = "HIGH",
//        objective = "Lose"
    }
}