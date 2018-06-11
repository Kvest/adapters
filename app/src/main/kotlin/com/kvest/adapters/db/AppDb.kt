package com.kvest.adapters.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

const val DB_VERSION = 1
const val DB_NAME = "app.db"
@Database(entities = [TimeEntity::class], version = DB_VERSION)
abstract class AppDb : RoomDatabase() {
    abstract fun timeDao(): TimeDao
}