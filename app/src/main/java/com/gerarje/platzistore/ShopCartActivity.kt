package com.gerarje.platzistore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_shop_cart.*
import org.jetbrains.anko.db.select

class ShopCartActivity : AppCompatActivity() {

    val items = arrayListOf<ItemLanding>()
    lateinit var adapter: AdapterShopCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)

        rcViewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = AdapterShopCart(items)
        rcViewCart.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val db = DBOpenHelper.getInstance(this)
        db?.use {
            select("Productos").exec {
                Log.e("Columnas:", "${this.columnCount}")
                Log.e("Columnas:", "${this.columnNames.size}")

                (this.columnNames).map {
                    Log.e("Columna: ", "$it")
                    Log.e("Columna: ", "${this.getColumnIndex(it)}")
                    Log.e("Columna: ", "${this.getColumnName(this.getColumnIndex(it))}")
                }

                this.moveToFirst()
                do {
                    Log.e("VALUE", this.getString(1) ?: "")
                    Log.e("VALUE", this.getString(2) ?: "")
                    Log.e("VALUE", "${this.getDouble(3)}")
                    Log.e("VALUE", this.getString(4) ?: "")


                    items.add(
                        ItemLanding(
                            this.getString(1) ?: "",
                            this.getString(2) ?: "",
                            this.getDouble(3),
                            this.getString(4) ?: ""
                            )
                    )
                    adapter.notifyDataSetChanged()
                } while (this.moveToNext())
            }
        }
    }
}
