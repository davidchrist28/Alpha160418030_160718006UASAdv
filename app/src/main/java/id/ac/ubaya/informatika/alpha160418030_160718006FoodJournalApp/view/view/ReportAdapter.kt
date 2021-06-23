package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.ReportLayoutBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User

class ReportAdapter(val reportList: ArrayList<Log>): RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {
    class ReportViewHolder(var view: ReportLayoutBinding): RecyclerView.ViewHolder(view.root)

    fun updateList(newList: List<Log>) {
        reportList.clear()
        reportList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ReportLayoutBinding>(inflater, R.layout.report_layout, parent, false)
        return ReportViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val currLog = reportList[position]
        holder.view.log = currLog

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
        } else {
            bmr = bmr
        }

        return bmr
    }

}