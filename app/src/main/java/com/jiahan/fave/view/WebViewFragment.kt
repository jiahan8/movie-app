package com.jiahan.fave.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiahan.fave.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWebviewBinding.inflate(inflater, container, false)

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl( "https://www.cathaycineplexes.com.sg/" )

        return binding.root
    }

}