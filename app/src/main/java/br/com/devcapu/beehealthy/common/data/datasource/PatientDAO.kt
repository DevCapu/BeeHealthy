package br.com.devcapu.beehealthy.common.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.devcapu.beehealthy.common.data.model.PatientAndBodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.common.data.model.PatientEntity

@Dao
interface PatientDAO {

    @Insert
    fun insert(patient: PatientEntity): Long

    @Query("SELECT * FROM patient WHERE email = :email")
    suspend fun findWithEmail(email: String): PatientAndBodyCalorieNeedsEntity
}
