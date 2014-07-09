package com.iamemustan.tsubufc_board

import android.app.Activity
import com.parse.ui.ParseLoginBuilder
import com.parse.ParseUser
import android.content.Intent

/**
 * Created by watyaa on 2014/06/28.
 */
public class DispatchLoginActivity() : Activity() {
    class object {
        val LOGIN_REQUEST = 0
    }

    var mDispatchLoginUI = false

    override fun onStart() {
        super<Activity>.onStart()
        val currentUser = ParseUser.getCurrentUser()
        if (currentUser == null) {
            //未ログイン
            if (!mDispatchLoginUI) {
                val loginBuilder = ParseLoginBuilder(this)
                startActivityForResult(loginBuilder.build(), LOGIN_REQUEST)
                mDispatchLoginUI = true
            }else{
                finish()
            }
        } else {
            //ログイン成功
            finish()
            startActivity(Intent(this, javaClass<BoardActivity>()))
        }

    }
}
