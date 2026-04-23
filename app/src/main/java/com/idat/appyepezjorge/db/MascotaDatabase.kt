package com.idat.appyepezjorge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.idat.appyepezjorge.model.Mascota

@Database(entities = [Mascota::class], version = 1, exportSchema = false)
abstract class MascotaDatabase : RoomDatabase() {

    abstract fun mascotaDao(): MascotaDao

    companion object {
        @Volatile
        private var INSTANCE: MascotaDatabase? = null

        fun getDatabase(context: Context): MascotaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MascotaDatabase::class.java,
                    "bdPT60870711"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}