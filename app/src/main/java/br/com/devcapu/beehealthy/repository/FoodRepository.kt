package br.com.devcapu.beehealthy.repository

import br.com.devcapu.beehealthy.dao.DatabaseFoodDataSource
import br.com.devcapu.beehealthy.local.LocalFoodDataSource
import br.com.devcapu.beehealthy.entity.IngestedFoodEntity
import br.com.devcapu.beehealthy.model.Food

class FoodRepository(
    private val localFoodDataSource: LocalFoodDataSource,
    private val databaseFoodDataSource: DatabaseFoodDataSource
) {

    fun getAll(foodAsJSONString: String) = localFoodDataSource.getAll(foodAsJSONString)

    fun addFood(food: Food, quantity: Int) {
        databaseFoodDataSource.save(IngestedFoodEntity.from(food, quantity))
    }
}