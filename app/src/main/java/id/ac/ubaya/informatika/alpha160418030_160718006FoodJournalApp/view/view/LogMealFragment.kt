package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val formatter = SimpleDateFormat("DDDD/MM/dd/yyyy")
    private lateinit var viewModel: LogViewModel
    private lateinit var dataBinding: FragmentLogMealBinding

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
        var dateNow = formatter.format(Date()).toString()
        txtDate.setText(dateNow)
        dataBinding.log = Log("", "", dateNow, uid)
        viewModel.fetch(uid.toString())

        observeViewModel()
    }

    override fun onButtonInputLog(v: View, log: Log) {
        viewModel.addLog(log)
        Navigation.findNavController(v).popBackStack()
    }

    fun observeViewModel() {
        viewModel.logLD.observe(viewLifecycleOwner, Observer {
            var cal = calculate(it)
            txtCal.setText(cal)
        })
    }

    fun calculate(logs: List<Log>): String {
        var totalCal: Int = 0
        logs.forEach {
            totalCal += it.calories.toInt()
        }
        return totalCal.toString()
    }
}