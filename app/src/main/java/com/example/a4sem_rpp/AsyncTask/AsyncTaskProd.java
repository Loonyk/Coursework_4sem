package com.example.a4sem_rpp.AsyncTask;

import android.os.AsyncTask;

import com.example.a4sem_rpp.MenuActivity;
import com.example.a4sem_rpp.db.AppDatabase;
import com.example.a4sem_rpp.modelDB.Products;

import java.util.List;

/** AsyncTask для выгрузки всех товаров в список*/
public class AsyncTaskProd extends AsyncTask<MenuActivity, Void, Void> {


    @Override
    protected Void doInBackground(MenuActivity... menuActivities) {

        MenuActivity menuAct = menuActivities[0];
        AppDatabase database = AppDatabase.getDatabase(menuAct);
        List<Products> list = database.getProductsDao().getAllProducts();
        // List<Notes> list = database.getNotesDao().getNotesId(ListAdapter.current_position);
        menuAct.openProducts(list);
        return null;
    }
}
