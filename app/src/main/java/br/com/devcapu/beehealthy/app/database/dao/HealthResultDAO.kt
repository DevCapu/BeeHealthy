package br.com.devcapu.beehealthy.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.devcapu.beehealthy.app.database.entity.BodyCalorieNeedsEntity

@Dao
interface HealthResultDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BodyCalorieNeedsEntity): Long
}