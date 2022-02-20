package br.com.devcapu.beehealthy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.devcapu.beehealthy.data.database.entity.PatientEntity

@Dao
interface PatientDAO {

    @Insert
    fun insert(patient: PatientEntity): Long
}
