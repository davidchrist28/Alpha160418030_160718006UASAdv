package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.view.View
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User

interface ButtonStartClickListener {
    fun onButtonStartClick(v:View)
}

interface ButtonUpdateProfileListener {
    fun onButtonUpdateProfile(v: View, user: User)
}

interface RadioGenderClickListener {
    fun onRadioGenderClick(v: View)
}

interface  RadioClickListener {
    fun onRadioClick(v: View)
}