package com.iamemustan.tsubufc_board

import com.parse.ui.ParseLoginDispatchActivity

/**
 * Created by watyaa on 2014/06/28.
 */
public class LoginDispatchActivity() : ParseLoginDispatchActivity() {

    override fun getTargetClass(): Class<*> {
        return javaClass<MainActivity>()
    }
}
