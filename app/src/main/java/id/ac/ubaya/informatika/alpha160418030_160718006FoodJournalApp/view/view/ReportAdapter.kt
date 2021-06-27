package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.ReportLayoutBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import kotlinx.android.synthetic.main.fragment_food_log.*

class ReportAdapter(val ReportList: ArrayList<Log>, val bmr: Double): RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {
    class ReportViewHolder(var view: ReportLayoutBinding): RecyclerView.ViewHolder(view.root)

    fun updateList(newList: List<Log>) {
        ReportList.clear()
        ReportList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ReportLayoutBinding>(inflater, R.layout.report_layout, parent, false)
        return ReportViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ReportList.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.view.log = ReportList[position]

        var edge: Double = bmr / 2
        var calNow: Double = ReportList[position].calories.toDouble()

        with(holder.view.txtStat) {
            if (calNow <= edge) {
                setText("LOW")
            } else if (calNow > edge && calNow <= bmr) {
                setText("NORMAL")
            } else {
                setText("EXCEED")
            }
        }

    }
}