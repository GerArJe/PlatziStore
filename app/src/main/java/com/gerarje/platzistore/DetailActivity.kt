package com.gerarje.platzistore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.let {
            val textFromMain:String = it.extras
                .getString("text")
            txtDetail.text = textFromMain
        }
    }
}
