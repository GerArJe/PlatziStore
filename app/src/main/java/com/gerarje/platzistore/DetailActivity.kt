package com.gerarje.platzistore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_description.*
import kotlinx.android.synthetic.main.item_landing.view.*
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
            val url = it.getString("url")
            txtDetailTitulo.text = it.getString("title")
            txtDetailDesc.text = it.getString("desc")
            txtDetailPrice.text = "$ ${String.format("%.2f", it.getDouble("price"))}"
            Glide.with(imageHeaderDetail).load(url).into(imageHeaderDetail)

            btnDetailBuy.setOnClickListener {
                db?.use {
                    val namePr = "name" to titl
                    val descPr = "Desc" to decr
                    val pricePr = "price" to price
                    val urlPr = "url" to url
                    insert("Productos", namePr, descPr, pricePr, urlPr)
                }
                startActivity<ShopCartActivity>()
            }
        }
    }
}
