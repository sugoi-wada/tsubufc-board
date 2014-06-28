package com.iamemustan.tsubufc_board

import android.content.Context
import android.widget.TextView
import kotlin.properties.Delegates
import android.widget.LinearLayout
import android.view.ContextMenu
import android.util.AttributeSet


/**
 * Created by watyaa on 2014/06/28.
 */

public open class PostItemView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    override fun createContextMenu(menu: ContextMenu?) {
        throw UnsupportedOperationException()
    }
    var mAuthorTextView: TextView by Delegates.notNull()
    var mCommentTextView: TextView by Delegates.notNull()


    override fun onFinishInflate() {
        super<LinearLayout>.onFinishInflate()
        mAuthorTextView = findViewById(R.id.author) as TextView
        mCommentTextView = findViewById(R.id.comment) as TextView
    }

    public fun setValues(post: Post) {
        mAuthorTextView.setText(post.name)
        mCommentTextView.setText(post.comment);
    }
}

