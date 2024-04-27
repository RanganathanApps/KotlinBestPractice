package com.example.kotlinbestpractice.utils

import android.util.Log
import java.lang.IllegalArgumentException

/** Created By Ranga
on 11-04-2024
 **/
class RegistrationUtils {

    fun register(name:String, email: String?, mobile: Long?, age: Int) {
        if(email.isNullOrEmpty()){
            throw IllegalArgumentException("email must not be null or empty!")
        }
        if(age<18){
            throw IllegalArgumentException("age must be 18 and more")
        }

        makeLog("s","n")
    }

    private fun makeLog(vararg msg:String){
        if (msg.isNotEmpty()) {
            if (msg.size > 1) {
                Log.w(msg[0], msg[1])
            } else {
                Log.w("Base", msg[0])
            }
        }else{
            Log.w("Base", "empty log msg!")
        }
    }
}