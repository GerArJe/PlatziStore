package com.gerarje.platzistore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.gerarje.platzistore.pojosretrofit.PayloadItem
import com.gerarje.platzistore.pojosretrofit.ResponseProducts
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcViewLanding.layoutManager =
            LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.71:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofit.create(Endpoints::class.java)

        val call = endpoint.getList()

        call.enqueue(object : Callback<ResponseProducts> {
            override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseProducts>, response: Response<ResponseProducts>) {
                if (response?.code() == 200){
                    Log.e("Respuesta", "${response.body().toString()}")

                    llenarRecycler(response.body()?.payload)
                }
            }

        })
    }

    private fun llenarRecycler(payload: List<PayloadItem?>?) {
        val productos = payload?.map {
            it?.let { (imgUrl, _, des, price, name, _) ->
                ItemLanding(name ?: "", des ?: "", price ?: 0.00, imgUrl ?: "")
            }
        }?.filter {
            val pr = it?.price ?: 0.00
            pr > 205.00
        }

        rcViewLanding.adapter = AdapterLanding(productos)
    }
}
