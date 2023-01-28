package br.com.devcapu.beehealthy.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class LocalCategoryDataSource {

    fun getAll(categoriesAsJSONString: String): List<Category> {
        val gson = Gson()
        val categoryListType = object : TypeToken<List<Category>>() {}.type

        return gson.fromJson(categoriesAsJSONString, categoryListType)
    }

    data class Category(
        val id: String,
        val category: String,
    )
}