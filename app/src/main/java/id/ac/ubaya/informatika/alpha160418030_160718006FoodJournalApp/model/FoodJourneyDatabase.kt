package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.util.MIGRATION_1_2

@Database(entities = arrayOf(User::class, Log::class), version = 2)
abstract class FoodJourneyDatabase: RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun logDao():LogDao

    companion object {
        @Volatile
        private var instance:FoodJourneyDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodJourneyDatabase::class.java,
            "foodjourneydb"
        ).addMigrations(MIGRATION_1_2).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}