package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    var profileLD = MutableLiveData<User>()
    private var job = Job()

    fun newUser(user: User) {
        launch {
            var datB = buildDB(getApplication())
            datB.userDao().insertUser(user)
        }
    }

    fun currUser(id: Int) {
        launch {
            var datB = buildDB(getApplication())
            profileLD.value = datB.userDao().selectSpecUsers(id)
        }
    }

    fun currUser(name: String) {
        launch {
            var datB = buildDB(getApplication())
            profileLD.value = datB.userDao().selectSpecUsers(name)
        }
    }

    fun updateUser(currUser: User, userNewD: User) {
        launch {
            var datB = buildDB(getApplication())
            datB.userDao().update(userNewD.name, userNewD.age, userNewD.gender, userNewD.height, userNewD.weight, currUser.id)
        }
    }

    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main
}