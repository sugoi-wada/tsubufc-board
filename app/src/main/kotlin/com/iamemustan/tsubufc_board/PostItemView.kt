package com.iamemustan.tsubufc_board

import android.content.Context
import android.widget.TextView
import kotlin.properties.Delegates
import android.util.AttributeSet
import android.widget.RelativeLayout
import java.text.SimpleDateFormat
import java.util.Date
import android.widget.ImageView
import com.nostra13.universalimageloader.core.ImageLoader


/**
 * Created by watyaa on 2014/06/28.
 */

public open class PostItemView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    var mAuthorTextView: TextView by Delegates.notNull()
    var mCommentTextView: TextView by Delegates.notNull()
    var mDateTextView: TextView by Delegates.notNull()
    var mAuthorImageView: ImageView by Delegates.notNull()
    val mFacebookPic = {(facebookId: String?) -> java.lang.String.format(FACEBOOK_GRAPH_PICTURE, facebookId) }

    override fun onFinishInflate() {
        super<RelativeLayout>.onFinishInflate()
        mAuthorTextView = findViewById(R.id.userName) as TextView
        mCommentTextView = findViewById(R.id.comment) as TextView
        mDateTextView = findViewById(R.id.date) as TextView
        mAuthorImageView = findViewById(R.id.imageView) as ImageView
    }

    public fun setValues(post: PostParseObject) {
        val imageLoader = ImageLoader.getInstance()
        imageLoader?.displayImage(mFacebookPic(post.getUser()?.fetchIfNeeded()?.getString("facebookId")), mAuthorImageView)
        mAuthorTextView.setText(post.getUser()?.fetchIfNeeded()?.getString("name"))
        mCommentTextView.setText(post.getComment())
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.format(Date(post.getDate() as Long))
        mDateTextView.setText(date)
    }


    class object {
        val FACEBOOK_GRAPH_PICTURE = "https://graph.facebook.com/%s/picture?type=square"
    }
}

