package com.gerarje.platzistore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtView.text = "Hola Android Extentions"

//        toastShort("Mensaje Toast Corto")

        toast("Mensaje Toast Corto desde Anko")

        txtView.setOnClickListener {
            startActivity<DetailActivity>("text" to "Hola desde Anko")
        }
    }
}
