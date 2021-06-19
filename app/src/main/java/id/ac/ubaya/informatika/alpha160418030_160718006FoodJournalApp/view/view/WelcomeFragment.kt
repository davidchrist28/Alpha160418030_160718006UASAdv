package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentWelcomeBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class WelcomeFragment : Fragment(), ButtonStartClickListener, RadioGenderClickListener, RadioClickListener {
    private lateinit var dataBinding: FragmentWelcomeBinding
    private lateinit var viewModel: ProfileViewModel
    lateinit var goal: String
    var genderCur: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater, R.layout.fragment_welcome, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.listener = this
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onButtonStartClick(v: View) {

        var name = txtNameW.text.toString()
        var age = txtAgeW.text.toString()
        var height = txtHeightW.text.toString()
        var weight = txtWeightW.text.toString()
        dataBinding.user = User(name, age, genderCur, height, weight)
        var id = dataBinding.user.id.toString()

        val arahin = WelcomeFragmentDirections.actionfoodLogFragment(id, goal)
        Navigation.findNavController(v).navigate(arahin)
    }

    override fun onRadioClick(v: View) {
        goal = v.tag.toString()
    }

    override fun onRadioGenderClick(v: View) {
        genderCur = v.tag.toString().toInt()
    }
}