package br.com.devcapu.beehealthy.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devcapu.beehealthy.common.domain.model.Food
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "ingested_food")
data class IngestedFoodEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val foodId: Int,
    val quantity: Int,
    val date: String,
) {
    companion object {
        fun from(
            food: Food,
            quantity: Int,
        ): IngestedFoodEntity {
            return IngestedFoodEntity(
                foodId = food.id.toInt(),
                quantity = quantity,
                date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            )
        }
    }
}