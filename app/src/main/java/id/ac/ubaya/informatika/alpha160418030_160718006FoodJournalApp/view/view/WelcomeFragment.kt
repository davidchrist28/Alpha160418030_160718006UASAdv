package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentWelcomeBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class WelcomeFragment : Fragment(), ButtonStartClickListener {
    private lateinit var dataBinding: FragmentWelcomeBinding

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
    }

    override fun onButtonStartClick(v: View, user: User) {
        var nama = txtNameW.text.toString()
        var age = txtAgeW.text.toString().toInt()
        var gender: Int
        var height = txtHeightW.text.toString().toInt()
        var weight = txtWeightW.text.toString().toInt()

        if (radioGroupGender.checkedRadioButtonId == R.id.radioMale) gender = 1
        else gender = 2

        val arahin = WelcomeFragmentDirections.actionItemFoodLog(nama, age, gender, height, weight)
        Navigation.findNavController(v).navigate(arahin)
    }
}