package by.chekun.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.chekun.repository.database.dao.CarDao
import by.chekun.repository.database.entity.Car


@Database(entities = [(Car::class)], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

}