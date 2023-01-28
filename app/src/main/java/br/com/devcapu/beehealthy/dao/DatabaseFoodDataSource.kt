package br.com.devcapu.beehealthy.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.devcapu.beehealthy.entity.IngestedFoodEntity

@Dao
interface DatabaseFoodDataSource {

    @Insert
    fun save(ingestedFoodEntity: IngestedFoodEntity)
}