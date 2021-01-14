package com.jiahan.fave.util

import com.jiahan.fave.network.Genre
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat

class UtilTest {

    @Test
    fun genreList_toString_isCorrect() {
        val genreList = listOf(
            Genre(1,"Family"),
            Genre(2, "Animation"),
            Genre(3, "Adventure") )
        assertThat(Util.getGenre(genreList), `is`("Family, Animation, Adventure"))
    }

    @Test
    fun genreList_toString_isNotCorrect() {
        val genreList = listOf(
            Genre(1,"Family"),
            Genre(2, "Animation"),
            Genre(3, "Adventure") )
        assertThat(Util.getGenre(genreList), `is`(not("Family,Animation,Adventure")))
    }

}