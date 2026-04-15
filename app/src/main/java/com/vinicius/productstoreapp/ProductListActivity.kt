package com.vinicius.productstoreapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var btnBack: Button
    private lateinit var db: ProductDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        db = ProductDatabase.getDatabase(this)
        recyclerView = findViewById(R.id.recyclerViewProducts)
        btnBack = findViewById(R.id.btnBack)

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnBack.setOnClickListener {
            finish()
        }

        loadProducts()
    }

    private fun loadProducts() {
        lifecycleScope.launch {
            val products = db.productDao().getAllProducts()
            adapter = ProductAdapter(products)
            recyclerView.adapter = adapter
        }
    }
}
