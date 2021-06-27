package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import android.provider.BlockedNumberContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ReportViewModel
import kotlinx.android.synthetic.main.fragment_report.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ReportFragment : Fragment() {
    private val formatter = SimpleDateFormat("MM/yyyy")
    private var adapter: ReportAdapter =  ReportAdapter(arrayListOf())
    private lateinit var viewModel: ReportViewModel
    private lateinit var currUser: User
    var bmr:Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currDate = formatter.format(Date())
        currUser = User("", "0", 1, "0", "0", "Maintain")
        txtDateR.setText(currDate)

        viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        viewModel.getUser()
        var uid = currUser.id
        viewModel.getReport(uid.toString(), currDate)

        recViewReport.layoutManager = LinearLayoutManager(context)
        recViewReport.adapter = adapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.UserLD.observe(viewLifecycleOwner, Observer {
            currUser = it
            bmr = calculateBMR(it)
        })

        viewModel.ReportLD.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it, bmr)
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
}