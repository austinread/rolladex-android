package io.github.austinread.rolladex.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.austinread.rolladex.daos.CharacterDao
import io.github.austinread.rolladex.entities.CharacterSheet

@Database(entities = [CharacterSheet::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var tempInstance = INSTANCE

                if (tempInstance == null) {
                    tempInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "rolladex_database"
                    )
                        .fallbackToDestructiveMigration()   //TODO: migration?
                        .build()
                    INSTANCE = tempInstance
                }
                return tempInstance
            }
        }
    }
}