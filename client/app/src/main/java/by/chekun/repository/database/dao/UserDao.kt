package by.chekun.repository.database.dao

import androidx.room.*
import by.chekun.repository.database.entity.Car
import by.chekun.repository.database.entity.User


@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)


    @Query("SELECT * FROM users LIMIT 1")
    fun getMe(): User

}