package id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.view.view

import android.view.View
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.Log
import id.ac.ubaya.informatika.alpha160418030_160718006FoodJournalApp.model.User

interface ButtonStartClickListener {
    fun onButtonStartClick(v:View, user: User)
}

interface ButtonUpdateProfileListener {
    fun onButtonUpdateProfile(v: View, user: User)
}

interface ButtonInputLogListener {
    fun onButtonInputLog(v: View, log: Log)
}

interface RadioGenderClickListener {
    fun onRadioGenderClick(v: View, user: User)
}

interface  RadioClickListener {
    fun onRadioClick(v: View, user: User)
}

interface FabClickListener {
    fun onFabClick(v: View, user: User)
}

interface LoadRecViewListener {
    fun onLoadRecView(v: View, Logs: List<Log>)
}