package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg user: User)

    @Query("SELECT * FROM users")
    suspend fun selectAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun selectSpecUsers(id: Int): User

    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun selectSpecUsers(name: String): User

    @Query("UPDATE users SET name = :name, age = :age, gender = :gender, height = :height, weight = :weight where id = :id")
    suspend fun update(name:String, age:String, gender:Int, height:String, weight:String, id:Int)

    @Delete
    suspend fun delete(user: User)
}