package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg user: User)

    @Query("SELECT * FROM users")
    suspend fun selectAllUsers(): List<User>

    @Query("UPDATE users SET name = :name, age = :age, gender = :gender, height = :height, weight = :weight where id = :id")
    suspend fun update(name:String, age:Int, gender:String, height:Int, weight:Int, id:Int)

    @Delete
    suspend fun delete(user: User)
}