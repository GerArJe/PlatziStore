package com.gerarje.platzistore

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_landing.view.*

class AdapterLanding(val data: List<ItemLanding?>?) : RecyclerView.Adapter<AdapterLanding.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder =
        Holder(p0.inflate(R.layout.item_landing))

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        data?.let {
            p0.bindView(it[p1])
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(itemLanding: ItemLanding?) {
            itemLanding?.let {
                with(it) {
                    itemView.txtTitleItem.text = title
                    itemView.txtDescItem.text = desc
                    itemView.txtPriceItem.text = "$ ${String.format("%.2f", price)}"

                    Glide.with(itemView.context).load(urlImage).into(itemView.imgItemHeader)

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailActivity::class.java)
                        intent.putExtra("title", title)
                        intent.putExtra("desc", desc)
                        intent.putExtra("price", price)
                        intent.putExtra("url", urlImage)

                        val p1: Pair<View, String> = Pair.create(itemView.imgItemHeader, "transitionHeader")
                        val p2: Pair<View, String> = Pair.create(itemView.txtTitleItem, "transitionTitle")
                        val p3: Pair<View, String> = Pair.create(itemView.txtDescItem, "transitionDesc")
                        val p4: Pair<View, String> = Pair.create(itemView.txtPriceItem, "transitionPrice")
                        val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity, p1, p2, p3, p4
                        )
                        itemView.context.startActivity(intent, options.toBundle())

                    }
                }
            }
        }
    }
}