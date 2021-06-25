package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        var name = intent.getStringExtra(WelcomeActivity.name).toString()
        var age = intent.getStringExtra(WelcomeActivity.age).toString()
        var gender = intent.getIntExtra(WelcomeActivity.gender, 1)
        var height = intent.getStringExtra(WelcomeActivity.height).toString()
        var weight = intent.getStringExtra(WelcomeActivity.weight).toString()
        var goal = intent.getStringExtra(WelcomeActivity.goal).toString()
        var userCurr = User(name, age, gender, height, weight, goal)
        viewModel.newUser(userCurr, false)

        navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}