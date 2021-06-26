package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentLogMealBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.LogViewModel
import kotlinx.android.synthetic.main.fragment_log_meal.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class LogMealFragment : Fragment(), ButtonInputLogListener {
    private val formatter = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var viewModel: LogViewModel
    private lateinit var dataBinding: FragmentLogMealBinding
    var bmr: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentLogMealBinding>(inflater, R.layout.fragment_log_meal, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        var uid = LogMealFragmentArgs.fromBundle(requireArguments()).id.toInt()
        bmr = LogMealFragmentArgs.fromBundle(requireArguments()).bmr.toDouble()
        var dateNow = formatter.format(Date()).toString()
        txtDate.setText(dateNow)
        dataBinding.log = Log("", "", dateNow, uid)
        dataBinding.listener = this
        viewModel.fetch(uid.toString())

        observeViewModel()
    }

    override fun onButtonInputLog(v: View, log: Log) {
        val valid = AlertDialog.Builder(activity)
        valid.setTitle("Validate Input")
        valid.setMessage("Please check and re-check your following inputs: \n \n Meal: "+log.foodName+" \n Calories(estimated): "+log.calories+" cal")
        valid.setPositiveButton("Next", DialogInterface.OnClickListener{ _, _->
            viewModel.addLog(log)
            Navigation.findNavController(v).popBackStack()
            Toast.makeText(v.context, "Don't forget of your target", Toast.LENGTH_SHORT).show()
        })
        valid.setNegativeButton("Cancel", DialogInterface.OnClickListener{ _, _->})
        valid.show()
    }

    fun observeViewModel() {
        viewModel.logLD.observe(viewLifecycleOwner, Observer {
            var cal = calculate(it)
            txtCal.text = cal.toString()
            var rem = bmr - cal
            txtRemain.text = rem.toString()
        })
    }

    fun calculate(logs: List<Log>): Double {
        var totalCal: Double = 0.0
        logs.forEach {
            totalCal += it.calories.toDouble()
        }
        return totalCal
    }
}