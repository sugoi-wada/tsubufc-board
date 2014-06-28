package com.iamemustan.tsubufc_board

import android.app.Application
import com.parse.Parse
import com.parse.ParseFacebookUtils

/**
 * Created by watyaa on 2014/06/28.
 */

public class TsubuFcApplication : Application() {

    override fun onCreate() {
        super<Application>.onCreate()
        // Required - Initialize the Parse SDK
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key))

        Parse.setLogLevel(Parse.LOG_LEVEL_WARNING)

        // Optional - If you don't want to allow Facebook login, you can
        // remove this line (and other related ParseFacebookUtils calls)
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id))

        // Optional - If you don't want to allow Twitter login, you can
        // remove this line (and other related ParseTwitterUtils calls)
        // ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret))
    }
}