package br.com.devcapu.beehealthy.model.nutrition

enum class Macro(
    val caloriesPerGram: Int,
    val dietPercentage: Int,
) {
    CARBOHYDRATE(caloriesPerGram = 4, dietPercentage = 50),
    FAT(caloriesPerGram = 9, dietPercentage = 25),
    PROTEIN(caloriesPerGram = 4, dietPercentage = 25);
}