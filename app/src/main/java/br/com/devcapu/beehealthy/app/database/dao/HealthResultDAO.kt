package br.com.devcapu.beehealthy.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.devcapu.beehealthy.app.database.entity.BodyCalorieNeedsEntity

@Dao
interface HealthResultDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BodyCalorieNeedsEntity): Long

    @Query("SELECT * FROM bodyCalorieNeeds WHERE patientId = :id")
    suspend fun findUserBodyCaloriesNeeds(id: Long): BodyCalorieNeedsEntity
}