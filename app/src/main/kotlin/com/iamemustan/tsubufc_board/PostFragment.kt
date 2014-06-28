package com.iamemustan.tsubufc_board

import android.app.Fragment
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import kotlin.properties.Delegates
import android.widget.TextView
import com.parse.ParseUser

/**
 * Created by watyaa on 2014/06/29.
 */

public class PostFragment : Fragment() {

    var mAuthorTextView: TextView by Delegates.notNull()
    var mCommentTextView: TextView by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        mAuthorTextView = view?.findViewById(R.id.author) as TextView
        mCommentTextView = view?.findViewById(R.id.comment) as TextView
        return view
    }


    override fun onStart() {
        super<Fragment>.onStart()
        val user = ParseUser.getCurrentUser()
        mAuthorTextView.setText(user?.getString("name"))
    }
}
