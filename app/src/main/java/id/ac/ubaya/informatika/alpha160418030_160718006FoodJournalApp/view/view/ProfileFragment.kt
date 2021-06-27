package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.R
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(), ButtonUpdateProfileListener, RadioGenderClickListener {
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
        dataBinding.user = User("", "", 1, "", "", "")
        currUser = User("", "", 1, "", "", "")
        dataBinding.listener = this
        dataBinding.radiolistener = this
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        viewModel.currUser()
        observeViewModel()
    }

    override fun onButtonUpdateProfile(v: View, user: User) {
        val valid = AlertDialog.Builder(activity)
        valid.setTitle("Validate Input")
        valid.setMessage("Please check and re-check your following inputs: \n \n Name: "+user.name+" \n Age: "+user.age+" \n Gender: "+user.gender+" \n Height: "+user.height+" \n Weight: "+user.weight+" \n Your Goal: "+user.goal)
        valid.setPositiveButton("Next", DialogInterface.OnClickListener{ _, _->
            viewModel.updateUser(currUser, user)
            Toast.makeText(v.context, "User profile updated", Toast.LENGTH_SHORT).show()
        })
        valid.setNegativeButton("Cancel", DialogInterface.OnClickListener{ _, _->})
        valid.show()
    }

    fun observeViewModel() {
        viewModel.profileLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
            currUser = it
        })
    }

    override fun onRadioGenderClick(v: View, user: User) {
        user.gender = v.tag.toString().toInt()
    }
}