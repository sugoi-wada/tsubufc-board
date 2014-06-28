package com.iamemustan.tsubufc_board

import android.app.Fragment
import android.widget.ListView
import kotlin.properties.Delegates
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.widget.ArrayAdapter
import android.content.Context

/**
 * Created by watyaa on 2014/06/28.
 */

public class BoardFragment : Fragment() {

    var mListView: ListView by Delegates.notNull()
    var mAdapter: BoardAdapter by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_board, container, false)
        mListView = view?.findViewById(android.R.id.list) as ListView
        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        mAdapter = BoardAdapter(getActivity() as Context)
        val post = Post()
        post.name = "nanashi"
        post.comment = "comment"

        mAdapter.add(post)
        mAdapter.add(post)
        mAdapter.add(post)
        mAdapter.add(post)
        mAdapter.add(post)
        mAdapter.add(post)


        mListView.setAdapter(mAdapter)

    }
}