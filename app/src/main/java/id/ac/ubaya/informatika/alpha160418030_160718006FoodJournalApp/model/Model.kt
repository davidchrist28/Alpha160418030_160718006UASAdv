package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    var name: String,
    var age: Int,
    @ColumnInfo(name = "gender")
    var gender: String,
    @ColumnInfo(name = "height")
    var height: Int,
    @ColumnInfo(name = "weight")
    var weight: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}