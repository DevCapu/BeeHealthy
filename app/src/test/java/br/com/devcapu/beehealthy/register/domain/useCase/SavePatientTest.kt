package br.com.devcapu.beehealthy.register.domain.useCase

import br.com.devcapu.beehealthy.common.domain.model.patient.Patient
import br.com.devcapu.beehealthy.common.data.repository.HealthRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.register.data.RegisterRepository
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SavePatientTest {

    @Test
    fun `should call repository when save patient`() = runBlocking {
        val patientRepository: PatientRepository = mockk(relaxed = true)
        val healthRepository: HealthRepository = mockk(relaxed = true)
        val registerRepository: RegisterRepository = mockk(relaxed = true)
        val savePatient = SavePatient(patientRepository, healthRepository, registerRepository)
        val patient = mockk<Patient>(relaxed = true)

        coEvery {
            registerRepository.register(any(), any(), any(), any())
        } answers {
            patientRepository.save(patient) { }
        }
        coEvery {
            patientRepository.save(patient, any())
        } coAnswers {
            healthRepository.save(mockk(relaxed = true), 0)
        }

        savePatient(
            patient = patient,
            password = "",
            onSuccess = { },
            onFailure = { }
        )

        coVerifySequence {
            registerRepository.register(any(), any(), any(), any())
            patientRepository.save(patient, any())
            healthRepository.save(any(), any())
        }
    }
}