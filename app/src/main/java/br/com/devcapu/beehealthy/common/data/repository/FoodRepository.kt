package br.com.devcapu.beehealthy.common.data.repository

import br.com.devcapu.beehealthy.common.data.datasource.LocalFoodDataSource

class FoodRepository(private val localFoodDataSource: LocalFoodDataSource) {

    fun getAll(foodAsJSONString: String) = localFoodDataSource.getAll(foodAsJSONString)
}