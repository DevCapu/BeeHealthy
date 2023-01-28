package br.com.devcapu.beehealthy.local

import br.com.devcapu.beehealthy.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalFoodDataSource {
    fun getAll(foodAsJSONString: String): List<Food> {
        val gson = Gson()
        val foodListType = object : TypeToken<List<Food>>() {}.type

        return gson.fromJson(foodAsJSONString, foodListType)
    }

}