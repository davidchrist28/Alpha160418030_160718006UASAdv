package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReportViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val ReportLD = MutableLiveData<List<Log>>()
    private val job = Job()

    fun getReport(id_user: String, date: String) {
        launch {
            var datB = buildDB(getApplication())
            ReportLD.value = datB.logDao().selectLogByDate(id_user, date)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}