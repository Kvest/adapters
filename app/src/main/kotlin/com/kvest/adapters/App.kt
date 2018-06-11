package com.kvest.adapters

import android.app.Application
import android.arch.persistence.room.Room
import com.kvest.adapters.db.AppDb
import com.kvest.adapters.db.DB_NAME

class App : Application() {
    private val db by lazy { Room.databaseBuilder(this, AppDb::class.java, DB_NAME).build() }
    val timeDao by lazy { db.timeDao() }
}