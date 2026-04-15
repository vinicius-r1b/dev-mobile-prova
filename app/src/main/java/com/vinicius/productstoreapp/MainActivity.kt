package com.vinicius.productstoreapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var editName: TextInputEditText
    private lateinit var editCode: TextInputEditText
    private lateinit var editPrice: TextInputEditText
    private lateinit var editQuantity: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnViewList: Button
    private lateinit var db: ProductDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = ProductDatabase.getDatabase(this)

        editName = findViewById(R.id.editProductName)
        editCode = findViewById(R.id.editProductCode)
        editPrice = findViewById(R.id.editProductPrice)
        editQuantity = findViewById(R.id.editProductQuantity)
        btnSave = findViewById(R.id.btnSaveProduct)
        btnViewList = findViewById(R.id.btnViewList)

        btnSave.setOnClickListener {
            saveProduct()
        }

        btnViewList.setOnClickListener {
            startActivity(Intent(this, ProductListActivity::class.java))
        }
    }

    private fun saveProduct() {
        val name = editName.text.toString().trim()
        val code = editCode.text.toString().trim()
        val priceStr = editPrice.text.toString().trim()
        val quantityStr = editQuantity.text.toString().trim()

        if (name.isEmpty() || code.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceStr.toDoubleOrNull()
        if (price == null || price <= 0) {
            Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val quantity = quantityStr.toIntOrNull()
        if (quantity == null || quantity <= 0) {
            Toast.makeText(this, "Quantidade inválida", Toast.LENGTH_SHORT).show()
            return
        }

        val product = Product(name = name, code = code, price = price, quantity = quantity)

        lifecycleScope.launch {
            db.productDao().insert(product)
            Toast.makeText(this@MainActivity, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }

    private fun clearFields() {
        editName.text?.clear()
        editCode.text?.clear()
        editPrice.text?.clear()
        editQuantity.text?.clear()
        editName.requestFocus()
    }
}
