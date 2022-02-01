package br.com.devcapu.beehealthy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.devcapu.beehealthy.data.database.dao.PatientDAO
import br.com.devcapu.beehealthy.data.database.entity.PatientEntity

@Database(entities = [PatientEntity::class], version = 1)
abstract class BeeHealthyDatabase: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "note.db"

        private var instance: BeeHealthyDatabase? = null

        private fun create(context: Context): BeeHealthyDatabase =
            Room.databaseBuilder(context, BeeHealthyDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context) = (instance ?: create(context)).also { instance = it }
    }

    abstract fun patientDao(): PatientDAO
}