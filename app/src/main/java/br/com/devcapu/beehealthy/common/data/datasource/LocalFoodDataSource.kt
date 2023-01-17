package br.com.devcapu.beehealthy.common.data.datasource

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalFoodDataSource {
    fun getAll(foodAsJSONString: String): List<Food> {
        val gson = Gson()
        val foodListType = object : TypeToken<List<Food>>() {}.type

        return gson.fromJson(foodAsJSONString, foodListType)
    }

    data class Food(
        val id: String,
        val description: String,
        val base_qty: String,
        val base_unit: String,
    )
}
