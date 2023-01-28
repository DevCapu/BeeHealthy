package br.com.devcapu.beehealthy.model

data class Food(
    val id: String,
    val description: String,
    val base_qty: String,
    val base_unit: String,
    val attributes: Attributes,
) {

    data class Attributes(
        val carbohydrate: Bla?,
        val protein: Bla?,
        val lipid: Bla?,
        private val energy: Energy?,
    ) {

        val calories: String
            get() = energy?.kcal ?: "0"

        data class Bla(
            val qty: String?,
            val unit: String? = "g",
        )

        data class Energy(
            val kcal: String?,
        )
    }
}
