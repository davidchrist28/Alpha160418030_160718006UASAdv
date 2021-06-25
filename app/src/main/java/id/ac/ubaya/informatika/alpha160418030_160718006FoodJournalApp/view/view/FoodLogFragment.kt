package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentFoodLogBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.LogViewModel
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import kotlinx.android.synthetic.main.fragment_food_log.txtCal
import kotlinx.android.synthetic.main.fragment_food_log.view.*
import kotlinx.android.synthetic.main.report_layout.*
import java.text.SimpleDateFormat
import java.util.*

class FoodLogFragment : Fragment(), FabClickListener {
    private val formatter = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var viewModel: LogViewModel
    private lateinit var dataBinding: FragmentFoodLogBinding
    private var adapter: FoodLogAdapter = FoodLogAdapter(arrayListOf())
    private lateinit var userCurr: User
    var calMax: Double = 0.0
    var cal: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentFoodLogBinding>(inflater, R.layout.fragment_food_log, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        dataBinding.userCurr = User("", "0", 1, "0", "0", "Maintain")
        userCurr = User("", "0", 1, "0", "0", "Maintain")
        dataBinding.fablistener = this
        txtDate.setText(formatter.format(Date()))

        viewModel.getUser()
        observeViewModel()
        var uid = userCurr.id
        viewModel.fetch(uid.toString())
        observeViewModel()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = adapter

        var edge: Double = calMax / 2
        var calNow: Double = cal

        if (calNow <= edge) {
            txtStatus.setText("LOW")
        } else if (calNow > edge && calNow <= calMax) {
            txtStatus.setText("NORMAL")
        } else {
            txtStatus.setText("EXCEED")
        }
    }

    override fun onFabClick(v: View, user: User) {
        val arahin = FoodLogFragmentDirections.actionLogMealFragment(user.id.toString())
        Navigation.findNavController(v).navigate(arahin)
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.userCurr = it
            userCurr = it
            if (it.gender == 1) {
                txtGender.setText("Male")
            } else {
                txtGender.setText("Female")
            }
            var bmr = calculateBMR(it)
            calMax = bmr
            txtMaxCal.text = calMax.toString()
        })

        viewModel.logLD.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
            var calTotal: Double = calculateCal(it)
            cal = String.format("%.2f", calTotal).toDouble()
            txtCal.setText(cal.toString())
            progsBarCal.progress = cal.toInt()
        })
    }


    fun calculateBMR(userNow: User): Double {
        var bmr: Double
        var weight: Double = userNow.weight.toDouble()
        var height: Double = userNow.height.toDouble()
        var age: Double = userNow.age.toDouble()

        if (userNow.gender == 1) {
            bmr = 13.397 * weight + 4.799 * height -  5.677 * age + 88.362
        } else {
            bmr = 9.247 * weight + 3.098 * height -  4.330 * age + 447.593
        }

        if (userNow.goal == "Gain") {
            bmr += bmr * 0.15
        } else if (userNow.goal == "Loss") {
            bmr -= bmr * 0.15
        }
        return bmr
    }

    fun calculateCal(logs: List<Log>): Double {
        var totalCal: Double = 0.0
        logs.forEach {
            totalCal += it.calories.toDouble()
        }
        return totalCal
    }
}