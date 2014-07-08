package com.iamemustan.tsubufc_board

import android.app.Fragment
import android.widget.ListView
import kotlin.properties.Delegates
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.content.Context
import android.widget.Button
import android.content.Intent
import com.parse.ParseQuery
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseFacebookUtils
import com.facebook.Request
import com.parse.ParseUser
import android.support.v4.widget.SwipeRefreshLayout

/**
 * Created by watyaa on 2014/06/28.
 */

public class BoardFragment : Fragment() {

    var mListView: ListView by Delegates.notNull()
    var mAdapter: BoardAdapter by Delegates.notNull()
    var mPostBtn: Button by Delegates.notNull()
    var mSwipeLayout: SwipeRefreshLayout by Delegates.notNull()
    val mUser = ParseUser.getCurrentUser()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_board, container, false)
        mListView = view?.findViewById(android.R.id.list) as ListView
        mPostBtn = view?.findViewById(R.id.postBtn) as Button
        mSwipeLayout = view?.findViewById(R.id.refresh) as SwipeRefreshLayout
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)
        mAdapter = BoardAdapter(getActivity() as Context)

        val session = ParseFacebookUtils.getSession();
        if (session != null && session.isOpened()) {
            makeMeRequest()
        }

        reload()
        mListView.setAdapter(mAdapter)
        mPostBtn.setOnClickListener { view ->
            startActivity(Intent(getActivity() as Context, javaClass<PostActivity>()))
        }

        mSwipeLayout.setColorSchemeResources(android.R.color.white, android.R.color.black, android.R.color.holo_green_light, android.R.color.holo_blue_light)
        mSwipeLayout.setOnRefreshListener { reload() }
    }

    fun reload() {
        val query = ParseQuery.getQuery(javaClass<PostParseObject>())
        query?.orderByDescending("date")?.findInBackground(object : FindCallback<PostParseObject>() {
            override fun done(list: List<PostParseObject>?, e: ParseException?) {
                if (e == null) {
                    mAdapter.clear()
                    mAdapter.addAll(list)
                    mSwipeLayout.setRefreshing(false);
                }
            }
        })
    }

    fun makeMeRequest() {
        Request.newMeRequest(ParseFacebookUtils.getSession(), {(user, response) ->
            if (user != null) {
                mUser?.put("facebookId", user.getId())
                mUser?.saveInBackground()
            }
        })?.executeAsync()
    }
}
