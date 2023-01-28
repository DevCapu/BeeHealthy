package br.com.devcapu.beehealthy.model.nutrition

import br.com.devcapu.beehealthy.model.nutrition.Macro.*
import br.com.devcapu.beehealthy.model.patient.health.CaloriesToCommitObjective

data class Macros(
    val carbohydrate: Int,
    val protein: Int,
    val fats: Int,
) {
    companion object {
        fun calculate(caloriesToCommitObjective: CaloriesToCommitObjective): Macros {
            val carbs = caloriesToCommitObjective.value.div(100).times(CARBOHYDRATE.dietPercentage)
                .div(CARBOHYDRATE.caloriesPerGram).toInt()
            val protein = caloriesToCommitObjective.value.div(100).times(PROTEIN.dietPercentage)
                .div(PROTEIN.caloriesPerGram).toInt()
            val fats = caloriesToCommitObjective.value.div(100).times(FAT.caloriesPerGram)
                .div(FAT.caloriesPerGram).toInt()

            return Macros(
                carbohydrate = carbs,
                protein = protein,
                fats = fats
            )
        }
    }
}
