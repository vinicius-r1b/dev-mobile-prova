package com.vinicius.productstoreapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase INSTANCE;
    public abstract ProductDao productDao();

    public static synchronized ProductDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class, "product_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
