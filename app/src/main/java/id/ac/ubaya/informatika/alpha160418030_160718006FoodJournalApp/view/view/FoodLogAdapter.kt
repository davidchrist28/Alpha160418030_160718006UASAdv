package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.LogLayoutBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log

class FoodLogAdapter(val foodLogList: ArrayList<Log>): RecyclerView.Adapter<FoodLogAdapter.FoodLogViewHolder>() {
    class FoodLogViewHolder(var view: LogLayoutBinding): RecyclerView.ViewHolder(view.root)

    fun updateList(logList: List<Log>) {
        foodLogList.clear()
        foodLogList.addAll(logList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<LogLayoutBinding>(inflater, R.layout.log_layout, parent, false)
        return FoodLogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodLogList.size
    }

    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        holder.view.log = foodLogList[position]
    }
}