package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.FoodJourneyDatabase

val DB_NAME = "foodjourneydb"

fun buildDB(context: Context): FoodJourneyDatabase {
    val db = Room.databaseBuilder(context, FoodJourneyDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2).build()

    return db
}

val MIGRATION_1_2 = object: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE users ADD COLUMN goal TEXT DEFAULT ''")
    }
}