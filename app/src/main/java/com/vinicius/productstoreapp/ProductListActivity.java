package com.vinicius.productstoreapp;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private Button btnBack;
    private ProductDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        db = ProductDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recyclerViewProducts);
        btnBack = findViewById(R.id.btnBack);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack.setOnClickListener(v -> finish());

        loadProducts();
    }

    private void loadProducts() {
        // Usando allowMainThreadQueries no banco para simplificar a lógica Java
        List<Product> products = db.productDao().getAllProducts();
        adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);
    }
}
