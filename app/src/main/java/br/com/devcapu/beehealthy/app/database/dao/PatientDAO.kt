package br.com.devcapu.beehealthy.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.devcapu.beehealthy.app.database.entity.BodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.app.database.entity.PatientAndBodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.app.database.entity.PatientEntity

@Dao
interface PatientDAO {

    @Insert
    suspend fun insert(patient: PatientEntity): Long

    @Query("SELECT * FROM patient WHERE email = :email")
    suspend fun findWithEmail(email: String): PatientAndBodyCalorieNeedsEntity
}
