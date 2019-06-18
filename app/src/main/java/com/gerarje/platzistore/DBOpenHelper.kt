package com.gerarje.platzistore

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "PlatziStore", null, 1) {

    companion object{
        private var instance:DBOpenHelper? = null

        fun getInstance(ctx: Context):DBOpenHelper? = if (instance == null){
            instance = DBOpenHelper(ctx)
            instance
        }else{
            instance
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val id = "id" to INTEGER + PRIMARY_KEY
        val name = "name" to TEXT
        val desc = "Desc" to TEXT
        val price = "price" to REAL
        val url = "url" to TEXT

        db?.createTable("Productos", true, id, name, desc, price, url)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("Productos", true)
    }

    val Context.database:DBOpenHelper?
    get() = getInstance(applicationContext)

}