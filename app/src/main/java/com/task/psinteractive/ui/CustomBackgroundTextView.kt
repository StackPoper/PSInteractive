package com.task.psinteractive.ui

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat

class CustomBackgroundTextView: androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // Makes possible for the TextViews to set their background in the BindingAdapters class
    fun setBackgroundImageResource(resourceId: Int) {
        background = ResourcesCompat.getDrawable(resources, resourceId, context.theme)
    }
}