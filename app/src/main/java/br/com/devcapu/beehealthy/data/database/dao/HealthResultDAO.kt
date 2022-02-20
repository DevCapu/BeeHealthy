package br.com.devcapu.beehealthy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.devcapu.beehealthy.data.database.entity.HealthResultEntity

@Dao
interface HealthResultDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HealthResultEntity): Long
}