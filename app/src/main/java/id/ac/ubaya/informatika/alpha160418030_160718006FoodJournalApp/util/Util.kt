package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.util

import android.content.Context
import androidx.room.Room
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.FoodJourneyDatabase

val DB_NAME = "foodjourneydb"

fun buildDB(context: Context): FoodJourneyDatabase {
    val db = Room.databaseBuilder(context, FoodJourneyDatabase::class.java, DB_NAME).build()

    return db
}