package com.vermaji.noteshare.loginService.session

import android.content.Context
import com.vermaji.noteshare.loginService.models.UserLoginOP

class SessionManagement(val context: Context) {
    private val TAG = "SessionManagement"
    private val sharedPreferences = context.getSharedPreferences("NoteShareUser",Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    companion object{
        const val IS_LOGIN = "IsLogged"
        const val KEY_FIRST_NAME = "firstName"
        const val KEY_LAST_NAME = "lastName"
        const val KEY_EMAIL = "email"
        const val KEY_USER_ID = "userid"
    }
    fun saveUserInfo(user: UserLoginOP):Boolean{
        editor.putString(KEY_FIRST_NAME,user.firstname)
        editor.putString(KEY_LAST_NAME,user.lastname)
        editor.putString(KEY_EMAIL,user.email)
        editor.putString(KEY_USER_ID,user.id.toString())
        editor.putBoolean(IS_LOGIN,true)
        editor.apply()
        return true
    }

    fun getUserInfo(): UserLoginOP{
        return UserLoginOP(
            id = sharedPreferences.getString(KEY_USER_ID,"")!!,
            firstname = sharedPreferences.getString(KEY_FIRST_NAME,"")!!,
            lastname = sharedPreferences.getString(KEY_LAST_NAME,"")!!,
            email = sharedPreferences.getString(KEY_EMAIL,"")!!

        )
    }

    fun logoutUser(){
        editor.remove(KEY_USER_ID)
        editor.remove(KEY_FIRST_NAME)
        editor.remove(KEY_LAST_NAME)
        editor.remove(KEY_EMAIL)
        editor.putBoolean(IS_LOGIN,false)
    }

    fun checkUserLogin(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGIN, false)
    }

}