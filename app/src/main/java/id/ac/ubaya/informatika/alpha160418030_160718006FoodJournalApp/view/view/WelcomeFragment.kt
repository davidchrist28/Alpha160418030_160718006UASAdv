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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater, R.layout.fragment_welcome, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.user = User("", "", 1, "", "", "")
        dataBinding.listener = this
        dataBinding.radiolistener = this
        dataBinding.radiotarget = this
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onButtonStartClick(v: View, user: User) {
        viewModel.newUser(user, true)
        var id = dataBinding.user?.id.toString()

        val arahin = WelcomeFragmentDirections.actionfoodLogFragment(id)
        Navigation.findNavController(v).navigate(arahin)
    }

    override fun onRadioClick(v: View, user: User) {
        user.goal = v.tag.toString()
    }

    override fun onRadioGenderClick(v: View, user: User) {
        user.gender = v.tag.toString().toInt()
    }

    fun observeViewModel() {
        viewModel.profileLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }
}