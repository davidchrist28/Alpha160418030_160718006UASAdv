package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model

import androidx.room.*

@Dao
interface LogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(vararg log: Log)

    @Query("SELECT * FROM foodLogs")
    suspend fun selectAllLogs(): List<Log>

    @Query("SELECT * FROM foodLogs WHERE id_user = :id_user")
    suspend fun selectLogByUser(id_user: Int): List<Log>

    @Query("SELECT * FROM foodLogs WHERE logId = :id")
    suspend fun selectLog(id: Int): Log

    @Query("UPDATE foodLogs SET foodName = :fname, calories = :calories where logId = :id")
    suspend fun update(fname:String, calories:Int, id:Int)

    @Delete
    suspend fun delete(log: Log)
}