package com.trainee.appinventiv.cryptocurrency.utils


import com.trainee.appinventiv.cryptocurrency.BaseApplication
import android.content.SharedPreferences
import android.preference.PreferenceManager


object SharedPreferences {
    private var mInstance: SharedPreferences? = null

    private fun getInstance(): SharedPreferences {
        if (mInstance == null)
            mInstance = PreferenceManager.getDefaultSharedPreferences(BaseApplication.instance.applicationContext)
        return mInstance!!
    }
    fun addFav(id: String,key:String){
        val editor= getInstance().edit()

        editor.putString(id,key)
        editor.apply()
    }
    fun get(key: String):String{
        return getInstance().getString(key,"")!!
    }
}