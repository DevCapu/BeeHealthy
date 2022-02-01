package br.com.devcapu.beehealthy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PatientDAO {

    @Insert
    fun saveBMI(bmi: Float)
}
