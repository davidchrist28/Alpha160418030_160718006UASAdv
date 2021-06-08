package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.LogReportLayoutBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log

class FoodLogAdapter(val foodLogList: ArrayList<Log>): RecyclerView.Adapter<FoodLogAdapter.FoodLogViewHolder>() {
    class FoodLogViewHolder(var view: LogReportLayoutBinding): RecyclerView.ViewHolder(view.root)

    fun updateList(logList: List<Log>) {
        foodLogList.clear()
        foodLogList.addAll(logList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<LogReportLayoutBinding>(inflater, R.layout.log_report_layout, parent, false)
        return FoodLogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodLogList.size
    }

    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}