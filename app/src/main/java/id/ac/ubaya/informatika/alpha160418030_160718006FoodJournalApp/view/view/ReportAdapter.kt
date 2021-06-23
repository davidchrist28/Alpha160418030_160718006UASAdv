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
        holder.view.txtStat.setText("LEMUUUUU")
    }
}