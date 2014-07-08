package com.iamemustan.tsubufc_board

import android.app.Fragment
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import kotlin.properties.Delegates
import android.widget.TextView
import com.parse.ParseUser
import android.view.MenuInflater
import android.view.Menu
import android.view.MenuItem
import com.parse.SaveCallback
import com.parse.ParseException

/**
 * Created by watyaa on 2014/06/29.
 */

public class PostFragment : Fragment() {

    var mAuthorTextView: TextView by Delegates.notNull()
    var mCommentTextView: TextView by Delegates.notNull()
    val mUser = ParseUser.getCurrentUser()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        mAuthorTextView = view?.findViewById(R.id.author) as TextView
        mCommentTextView = view?.findViewById(R.id.comment) as TextView
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super<Fragment>.onStart()
        mAuthorTextView.setText(mUser?.getString("name"))
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val inflater = getActivity()?.getMenuInflater()
        inflater?.inflate(R.menu.post, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.getItemId()) {
            R.id.postBtn -> post()
            else -> super<Fragment>.onOptionsItemSelected(item)
        }
    }

    fun post(): Boolean {
        val post = PostParseObject()
        post.setUser(mUser)
        post.setComment(mCommentTextView.getText().toString())
        post.setDate(System.currentTimeMillis())
        post.saveInBackground(object : SaveCallback() {

            override fun done(e: ParseException?) {
                getActivity()?.finish()
            }
        })
        return true
    }

}
