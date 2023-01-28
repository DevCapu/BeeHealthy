package br.com.devcapu.beehealthy.repository

class MealRepository {

    fun findMeal(id: Long?): Meal {
        return Meal()
    }
}

data class Meal(
    val name: String = "Almoço"
)