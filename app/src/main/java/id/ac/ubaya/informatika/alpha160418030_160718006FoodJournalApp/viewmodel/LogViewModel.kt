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
    private var job = Job()

    fun newUser(user: User) {
        launch {
            var datB = buildDB(getApplication())
            datB.userDao().insertUser(user)
        }
    }

    fun fetch(id: String) {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = datB.logDao().selectLogByUser(id)
        }
    }

    fun fetch(user: User) {
        launch {
            var datB = buildDB(getApplication())
            var id = user.id.toString()
            logLD.value = datB.logDao().selectLogByUser(id)
        }
    }

    fun selectSpecific(log_id: Int) {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = listOf(datB.logDao().selectLog(log_id))
        }
    }

    fun refresh() {
        launch {
            var datB = buildDB(getApplication())
            logLD.value = datB.logDao().selectAllLogs()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}