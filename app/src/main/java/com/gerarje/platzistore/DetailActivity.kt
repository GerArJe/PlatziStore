package com.gerarje.platzistore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_description.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val db = DBOpenHelper.getInstance(this)

        intent?.extras?.let {
            val titl = it.getString("title")
            val decr = it.getString("desc")
            val price = it.getString("price")
            txtDetailTitulo.text = it.getString("title")
            txtDetailDesc.text = it.getString("desc")
            txtDetailPrice.text = "$ ${String.format("%.2f", it.getDouble("price"))}"

            btnDetailBuy.setOnClickListener {
                db?.use {
                    val namePr = "name" to titl
                    val descPr = "Desc" to decr
                    val pricePr = "price" to price
                    insert("Productos", namePr, descPr, pricePr)
                }
                startActivity<ShopCartActivity>()
            }
        }
    }
}
