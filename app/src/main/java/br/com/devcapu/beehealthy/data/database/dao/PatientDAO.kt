package br.com.devcapu.beehealthy.data.database.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PatientDAO {

    @Query("INSERT INTO patient (bmi) VALUES (:bmi)")
    fun saveBMI(bmi: Float)
}
