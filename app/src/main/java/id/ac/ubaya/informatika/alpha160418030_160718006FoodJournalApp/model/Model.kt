package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model

import androidx.room.*
import kotlin.reflect.KClass

@Entity(tableName = "users")
data class User (
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "age")
    var age: String,
    @ColumnInfo(name = "gender")
    var gender: Int,
    @ColumnInfo(name = "height")
    var height: String,
    @ColumnInfo(name = "weight")
    var weight: String,
    @ColumnInfo(name = "goal")
    var goal: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(tableName = "foodLogs")
data class Log (
    @ColumnInfo(name = "foodName")
    var foodName: String,
    @ColumnInfo(name = "calories")
    var calories: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "id_user")
    var id_user: Int
) {
    @PrimaryKey(autoGenerate = true)
    var logId: Int = 0
}

data class UserWithFoodLog (
    @Embedded val user: User, @Relation(parentColumn = "id", entityColumn = "id_user") val log: List<Log>
)