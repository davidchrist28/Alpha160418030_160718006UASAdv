package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.ActivityWelcomeBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User

class WelcomeActivity : AppCompatActivity(), ButtonStartClickListener, RadioGenderClickListener, RadioClickListener {
    private lateinit var dataBinding: ActivityWelcomeBinding

    companion object {
        val name = "jenengku"
        val age = "umurku"
        val gender = ""
        val height = "dhuwurku"
        val weight = "bobotku"
        val goal = "karepku"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        dataBinding.user = User("", "", 1, "", "", "")
        dataBinding.listener = this
        dataBinding.radiolistener = this
        dataBinding.radiotarget = this

    }

    override fun onButtonStartClick(v: View, user: User) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra(name, user.name)
        intent.putExtra(age, user.age)
        intent.putExtra(gender, user.gender)
        intent.putExtra(height, user.height)
        intent.putExtra(weight, user.weight)
        intent.putExtra(goal, user.goal)
        startActivity(intent)

    }

    override fun onRadioGenderClick(v: View, user: User) {
        user.gender = v.tag.toString().toInt()
    }

    override fun onRadioClick(v: View, user: User) {
        user.goal = v.tag.toString()
    }
}