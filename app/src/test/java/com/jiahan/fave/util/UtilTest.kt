package com.jiahan.fave.util

import com.jiahan.fave.network.Genre
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class UtilTest {

    @Test
    fun genreList_toString_isCorrect() {
        val genreList = listOf(
            Genre(1,"Family"),
            Genre(2, "Animation"),
            Genre(3, "Adventure") )
        assertThat(Util.getGenre(genreList), `is`("Family, Animation, Adventure"))
    }

}