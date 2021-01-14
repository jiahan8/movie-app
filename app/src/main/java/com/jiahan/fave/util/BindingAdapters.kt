package com.jiahan.fave.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.jiahan.fave.database.DatabaseMovie

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if(it == null) View.VISIBLE else View.GONE
}

@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if(it == null) View.GONE else View.VISIBLE
}

/**
 * Binding adapter used to display images from URL using Fresco
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: SimpleDraweeView, image: DatabaseMovie) {
    val url = Util.getImageURL(image.poster_path)
    imageView.setImageURI( url )
}

/**
 * Binding adapter used to truncate long text
 */
@BindingAdapter("imageTitle")
fun setImageTitle(textView: TextView, title: String) {
    var sentence = title
    if (sentence.length > 60) {
        sentence = sentence.substring(0, 60) + "..."
    }
    textView.text = sentence
}

