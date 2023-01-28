package br.com.devcapu.beehealthy.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.devcapu.beehealthy.entity.BodyCalorieNeedsEntity
import br.com.devcapu.beehealthy.entity.IngestedFoodEntity
import br.com.devcapu.beehealthy.entity.PatientEntity

@Database(entities = [
    PatientEntity::class,
    BodyCalorieNeedsEntity::class,
    IngestedFoodEntity::class
], version = 1)
abstract class BeeHealthyDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "beeHealthy.db"

        private var instance: BeeHealthyDatabase? = null

        private fun create(context: Context): BeeHealthyDatabase =
            Room.databaseBuilder(context, BeeHealthyDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context) = (instance ?: create(context)).also { instance = it }
    }

    abstract fun patientDao(): PatientDAO
    abstract fun healthResultDao(): HealthResultDAO
    abstract fun ingestedFoodDao(): DatabaseFoodDataSource
}