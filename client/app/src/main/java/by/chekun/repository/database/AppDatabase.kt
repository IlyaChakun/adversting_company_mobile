package by.chekun.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.chekun.repository.database.dao.AdvertisementDao
import by.chekun.repository.database.dao.UserDao
import by.chekun.repository.database.entity.Car
import by.chekun.repository.database.entity.User


@Database(entities = [(Car::class), (User::class)], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun advertisementsDao(): AdvertisementDao
    abstract fun userDao(): UserDao

}