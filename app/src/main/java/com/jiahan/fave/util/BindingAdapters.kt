package com.jiahan.fave.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.jiahan.fave.database.Movie

/**
 * Binding adapter used to hide view when data is null.
 */
@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if(it == null) View.GONE else View.VISIBLE
}

/**
 * Binding adapter used to display images from URL using Fresco.
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: SimpleDraweeView, movie: Movie) {
    if( movie.poster_path != null )
        imageView.setImageURI( Util.getImageURL(movie.poster_path) )
    else
        imageView.visibility = View.GONE
}

/**
 * Binding adapter used to truncate long text.
 */
@BindingAdapter("movieTitle")
fun setMovieTitle(textView: TextView, title: String) {
    var sentence = title
    if (sentence.length > 60) {
        sentence = sentence.substring(0, 60) + "..."
    }
    textView.text = sentence
}

