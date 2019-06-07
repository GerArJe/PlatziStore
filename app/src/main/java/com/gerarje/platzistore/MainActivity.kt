package com.gerarje.platzistore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcViewLanding.layoutManager =
            GridLayoutManager(this, 2)

        val itemShop = (0..20).map {
            ItemLanding("Titulo $it", "Descripción $it", 200.000 + it)
        }

        val adapter = AdapterLanding(itemShop)
        rcViewLanding.adapter = adapter

    }
}
