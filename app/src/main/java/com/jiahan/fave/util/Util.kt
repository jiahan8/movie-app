package com.jiahan.fave.util

import com.jiahan.fave.network.Genre
import java.util.*

object Util{

    /**
     * Return full image url.
     */
    @JvmStatic
    fun getImageURL(secret: String) = "https://image.tmdb.org/t/p/w500$secret"

    @JvmStatic
    fun floatToString(f: Float) = f.toString()

    @JvmStatic
    fun getGenre(genres: List<Genre>?) = genres?.joinToString(separator = ", ") { it.name }

    /**
     * Convert ISO to full form.
     */
    @JvmStatic
    fun isoToLanguage( iso: String? ): String = if(iso != null){
        Locale(iso).getDisplayLanguage(Locale(iso))
    }else{
        ""
    }
}