package com.vinicius.productstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editName, editCode, editPrice, editQuantity;
    private Button btnSave, btnViewList;
    private ProductDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = ProductDatabase.getInstance(this);

        editName = findViewById(R.id.editProductName);
        editCode = findViewById(R.id.editProductCode);
        editPrice = findViewById(R.id.editProductPrice);
        editQuantity = findViewById(R.id.editProductQuantity);
        btnSave = findViewById(R.id.btnSaveProduct);
        btnViewList = findViewById(R.id.btnViewList);

        btnSave.setOnClickListener(v -> saveProduct());

        btnViewList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
    }

    private void saveProduct() {
        String name = editName.getText().toString().trim();
        String code = editCode.getText().toString().trim();
        String priceStr = editPrice.getText().toString().trim();
        String quantityStr = editQuantity.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            if (price <= 0) {
                Toast.makeText(this, "Preço deve ser positivo", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                Toast.makeText(this, "Quantidade deve ser positiva", Toast.LENGTH_SHORT).show();
                return;
            }

            Product product = new Product(name, code, price, quantity);
            
            // Usando allowMainThreadQueries para simplificar (conforme solicitado estilo dos repos)
            db.productDao().insert(product);
            
            Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
            clearFields();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valores numéricos inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editName.setText("");
        editCode.setText("");
        editPrice.setText("");
        editQuantity.setText("");
        editName.requestFocus();
    }
}
