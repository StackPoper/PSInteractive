package com.task.psinteractive

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.task.psinteractive.data.ShowInfo
import com.task.psinteractive.ui.CustomBackgroundTextView

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ShowInfo>?) {
    val adapter = recyclerView.adapter as ShowsListAdapter
    adapter.submitList(data)
}
@BindingAdapter("startTime")
fun bindStartTimeButton(t: CustomBackgroundTextView, time: Long?) {
    if (time != null && time > 0)
       t.text = time.toString()
    else
        t.setBackgroundImageResource(R.drawable.ic_broken_image)
}
@BindingAdapter("endTime")
fun bindEndTimeButton(t: CustomBackgroundTextView, time: Long?) {
    if (time != null && time > 0)
        t.text = time.toString()
    else
        t.setBackgroundImageResource(R.drawable.ic_broken_image)
}
@BindingAdapter("startPoint", "endPoint")
fun bindDurationButton(t: CustomBackgroundTextView, startPoint: Long?, endPoint: Long?) {
    if (startPoint != null && startPoint > 0 && endPoint != null && endPoint > 0 && endPoint > startPoint) {
        t.text = (endPoint - startPoint).toString()
    } else
        t.setBackgroundImageResource(R.drawable.ic_broken_image)
}
@BindingAdapter("episodeTitle")
fun bindEpisodeTitleButton(t: CustomBackgroundTextView, text: String?) {
    if (text?.isNotEmpty() == true) {
        t.text = text
        t.background = null
    } else
        t.setBackgroundImageResource(R.drawable.ic_broken_image)
}

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl == null)
        imgView.setImageResource(R.drawable.ic_broken_image)

    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}