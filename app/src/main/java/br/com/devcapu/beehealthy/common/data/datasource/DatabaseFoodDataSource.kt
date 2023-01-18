package br.com.devcapu.beehealthy.common.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import br.com.devcapu.beehealthy.common.data.model.IngestedFoodEntity

@Dao
interface DatabaseFoodDataSource {

    @Insert
    fun save(ingestedFoodEntity: IngestedFoodEntity)
}