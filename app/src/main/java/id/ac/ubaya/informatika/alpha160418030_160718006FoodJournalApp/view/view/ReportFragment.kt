package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentReportBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.ReportLayoutBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.LogViewModel
import kotlinx.android.synthetic.main.fragment_report.*
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment() {
    private val formatter = SimpleDateFormat("MM/yyyy")
    private var adapter: ReportAdapter = ReportAdapter(arrayListOf())
    private lateinit var viewModel: LogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtDateR.setText(formatter.format(Date()))
        var uid = ReportFragmentArgs.fromBundle(requireArguments()).id

        viewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        viewModel.fetch(uid)

        recViewR.layoutManager = LinearLayoutManager(context)
        recViewR.adapter = adapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.logLD.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }

//    fun byDates() {
//
//    }

    fun calculate(logs: List<Log>): String {
        var totalCal: Int = 0
        logs.forEach {
            totalCal += it.calories.toInt()
        }
        return totalCal.toString()
    }
}