package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentFoodLogBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentWelcomeBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.LogViewModel
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*

class FoodLogFragment : Fragment() {
    private lateinit var viewModel: LogViewModel
    private lateinit var proViewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentFoodLogBinding
    private var adapter: FoodLogAdapter = FoodLogAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentFoodLogBinding>(inflater, R.layout.fragment_food_log, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        proViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        var id = FoodLogFragmentArgs.fromBundle(requireArguments()).id
        proViewModel.currUser(id)

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = adapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.logLD.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        proViewModel.profileLD.observe(viewLifecycleOwner, Observer {
            dataBinding.userCurr = it
        })
    }
}