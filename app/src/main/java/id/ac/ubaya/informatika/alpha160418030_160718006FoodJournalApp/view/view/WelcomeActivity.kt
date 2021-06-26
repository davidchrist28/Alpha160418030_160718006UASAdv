package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.ActivityWelcomeBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ProfileViewModel

class WelcomeActivity : AppCompatActivity(), ButtonStartClickListener, RadioGenderClickListener, RadioClickListener {
    private lateinit var dataBinding: ActivityWelcomeBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.currUser()
        observeViewModel()
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        dataBinding.user = User("", "", 1, "", "", "")
        dataBinding.listener = this
        dataBinding.radiolistener = this
        dataBinding.radiotarget = this
    }

    override fun onButtonStartClick(v: View, user: User) {
        viewModel.newUser(user, false)
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onRadioGenderClick(v: View, user: User) {
        user.gender = v.tag.toString().toInt()
    }

    override fun onRadioClick(v: View, user: User) {
        user.goal = v.tag.toString()
    }

    fun observeViewModel() {
        viewModel.profileLD.observe(this, Observer {
            if (it != null) {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}