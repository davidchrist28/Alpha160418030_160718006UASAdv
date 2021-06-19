package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.ReportLayoutBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log

class ReportAdapter(val reportList: ArrayList<Log>): RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {
    class ReportViewHolder(var view: ReportLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}