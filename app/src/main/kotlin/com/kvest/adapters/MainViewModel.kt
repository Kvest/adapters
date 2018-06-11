package com.kvest.adapters

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kvest.adapters.db.TimeDao
import com.kvest.adapters.db.TimeEntity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import java.text.SimpleDateFormat

class MainViewModel(private val timeDao: TimeDao) : ViewModel(), ListItemHandler {
    private val TIME_FORMATTER = SimpleDateFormat("HH:mm:ss.SSS")

    val times by lazy { timeDao.listenTimeEntities()}
    val selectEvent by lazy { SingleLiveEvent<TimeEntity>() }
    fun addTime() {
        val currentTime = System.currentTimeMillis()
        val entity = TimeEntity(currentTime, TIME_FORMATTER.format(currentTime))
        launch(CommonPool) {
            timeDao.insertTimeEntity(entity)
        }
    }

    override fun onSelect(item: TimeEntity) {
        selectEvent.value = item
    }

    override fun onDelete(id: Long) {
        launch(CommonPool) {
            timeDao.deleteById(id)
        }
    }

    companion object {
        fun createFactory(timeDao: TimeDao): ViewModelProvider.Factory = Factory(timeDao)
    }

    private class Factory(private val timeDao: TimeDao): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = MainViewModel(timeDao) as T
    }
}