package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LogViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val logLD = MutableLiveData<List<Log>>()
    val userLD = MutableLiveData<User>()
    private var job = Job()

    fun getUser()
    {
        launch {
            var datB = buildDB(getApplication())
            userLD.value = datB.userDao().selectUser()
        }
    }

    fun getUser(id:Int)
    {
        launch {
            var datB = buildDB(getApplication())
            userLD.value = datB.userDao().selectSpecUsers(id)
        }
    }

    fun refresh() {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = datB.logDao().selectAllLogs()
        }
    }

    fun refresh(uid: String) {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = datB.logDao().selectLogByUser(uid)
        }
    }

    fun addLog(log: Log) {
        launch {
            var datB = buildDB(getApplication())
            datB.logDao().insertLog(log)
            refresh()
        }
    }

    fun fetch(uid: String) {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = datB.logDao().selectLogByUser(uid)
        }
    }

    fun fetch(user: User) {
        launch {
            var datB = buildDB(getApplication())
            var id = user.id.toString()
            logLD.value = datB.logDao().selectLogByUser(id)
        }
    }

    fun fetch(uid: String, date: String) {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = datB.logDao().selectLogByDate(uid, date)
        }
    }

    fun selectSpecific(log_id: Int) {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = listOf(datB.logDao().selectLog(log_id))
        }
    }

    fun deleteLog(log: Log) {
        launch {
            var datB = buildDB(getApplication())
            datB.logDao().delete(log)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}