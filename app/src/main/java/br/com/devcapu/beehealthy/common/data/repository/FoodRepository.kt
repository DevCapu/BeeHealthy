package br.com.devcapu.beehealthy.common.data.repository

import br.com.devcapu.beehealthy.common.data.datasource.DatabaseFoodDataSource
import br.com.devcapu.beehealthy.common.data.datasource.LocalFoodDataSource
import br.com.devcapu.beehealthy.common.data.model.IngestedFoodEntity
import br.com.devcapu.beehealthy.common.domain.model.Food

class FoodRepository(
    private val localFoodDataSource: LocalFoodDataSource,
    private val databaseFoodDataSource: DatabaseFoodDataSource
) {

    fun getAll(foodAsJSONString: String) = localFoodDataSource.getAll(foodAsJSONString)

    fun addFood(food: Food, quantity: Int) {
        databaseFoodDataSource.save(IngestedFoodEntity.from(food, quantity))
    }
}