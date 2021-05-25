package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlin.reflect.KClass

@Entity(tableName = "users")
data class User (
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "age")
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

@Entity(tableName = "foodLogs")
class Log (
    @ColumnInfo(name = "foodName")
    var foodName: String,
    @ColumnInfo(name = "calories")
    var calories: String
) {
//    @ForeignKey(entity = KClass<User>)
//    var id_user: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var logId: Int = 0
}