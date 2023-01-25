package com.jiahan.fave.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.jiahan.fave.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Fresco.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}