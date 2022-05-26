package by.chekun.repository.database.dao

import androidx.room.*
import by.chekun.repository.database.entity.Car


@Dao
interface CarDao {

    @Transaction
    @Query("SELECT * FROM cars")
    fun getAll(): List<Car>

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getById(id: Long): Car

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: Array<Car>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: Car)

    @Update
    fun updateAll(list: List<Car>)

    @Delete
    fun delete(user: Car)
}