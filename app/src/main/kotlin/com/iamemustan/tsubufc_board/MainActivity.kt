package com.iamemustan.tsubufc_board

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.os.Build
import com.parse.ParseUser
import android.content.Intent
import kotlin.properties.Delegates
import android.widget.Toast

/**
 * Created by watyaa on 2014/06/28.
 */

public class MainActivity : Activity() {
    private var mTitleTextView: TextView by Delegates.notNull()
    private var mEmailTextView: TextView by Delegates.notNull()
    private var mNameTextView: TextView by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Toast.makeText(this, "LoggedIn!", Toast.LENGTH_SHORT).show()
        mTitleTextView = findViewById(R.id.profile_title) as TextView
        mEmailTextView = findViewById(R.id.profile_email) as TextView
        mNameTextView = findViewById(R.id.profile_name) as TextView
        mTitleTextView.setText(R.string.profile_title_logged_in)

        findViewById(R.id.logout_button)?.setOnClickListener { view ->
            ParseUser.logOut()

            // FLAG_ACTIVITY_CLEAR_TASK only works on API 11, so if the user
            // logs out on older devices, we'll just exit.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                val intent = Intent(this, javaClass<LoginDispatchActivity>())
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Set up the profile page based on the current user.
        val user = ParseUser.getCurrentUser()
        showProfile(user)
    }

    /**
     * Shows the profile of the given user.
     *
     * @param user
     */
    private fun showProfile(user: ParseUser?) {
        if (user != null) {
            mEmailTextView.setText(user?.getEmail())
            val fullName = user?.getString("name")
            if (fullName != null) {
                mNameTextView.setText(fullName)
            }
        }
    }
}