package com.silassare.alc40challenge1

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_title_textview.view.*

class TitleTextView : LinearLayout {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        LayoutInflater.from(context).inflate(R.layout.view_title_textview, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.TitleTextView, 0, 0
            )

            val title = typedArray.getString(R.styleable.TitleTextView_tt_title)
            val value = typedArray.getString(R.styleable.TitleTextView_tt_value)

            title?.let { string ->
                title_tv.text = string
            }

            value?.let { string ->
                value_tv.text = string
            }

            typedArray.recycle()

        }


    }

}