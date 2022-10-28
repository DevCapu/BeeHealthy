package br.com.devcapu.beehealthy.domain.useCase

import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository
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
        val savePatient = SavePatient(patientRepository, healthRepository)
        val patient = mockk<Patient>()

        coEvery {
            patientRepository.save(patient, any())
        } coAnswers {
            healthRepository.save(mockk(relaxed = true), 0)
        }

        savePatient(patient)

        coVerifySequence {
            patientRepository.save(patient, any())
            healthRepository.save(any(), any())
        }
    }
}