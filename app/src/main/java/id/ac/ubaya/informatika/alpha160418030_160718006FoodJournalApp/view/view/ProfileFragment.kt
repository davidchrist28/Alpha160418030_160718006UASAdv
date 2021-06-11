package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(), ButtonUpdateProfileListener {
    private lateinit var dataBinding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var currUser: User


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.listener = this
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        var currUserName = ProfileFragmentArgs.fromBundle(requireArguments()).nameUser
        viewModel.currUser(currUserName)

        observeViewModel()
    }

    override fun onButtonUpdateProfile(v: View, user: User) {
        viewModel.updateUser(currUser, user)
    }

    fun observeViewModel() {
        viewModel.profileLD.observe(viewLifecycleOwner, Observer {
            currUser = it
        })
    }
}