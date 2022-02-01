package br.com.devcapu.domain.repository

class PatientRepository(private val patientDataSource: PatientDataSource) {
    fun saveBMI(bmi: Float) = patientDataSource.saveBMI(bmi)
}