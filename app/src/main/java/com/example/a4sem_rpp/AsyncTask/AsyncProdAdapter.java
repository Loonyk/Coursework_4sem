package com.example.a4sem_rpp.AsyncTask;

import android.os.AsyncTask;

import com.example.a4sem_rpp.db.AppDatabase;
import com.example.a4sem_rpp.modelDB.Products;
import com.example.a4sem_rpp.products.AdapterProducts;

import java.util.List;

/** AsyncTask для работы с адаптером списка товаров */
public class AsyncProdAdapter extends AsyncTask<AdapterProducts, Void, Void> {

    @Override
    protected Void doInBackground(AdapterProducts... prodAdapter) {

        AdapterProducts pAdapter = prodAdapter[0];
        AppDatabase database = AppDatabase.getDatabase(pAdapter.context);

        Products products = pAdapter.getProducts();
        products.setTitle_pr(pAdapter.getTitProdEt().toString());
        products.setPrice(pAdapter.getPriceEt().toString());
        products.setCount(pAdapter.getCountEt().toString());
//        products.setPrice(Integer.pAdapter.getPriceEt().toString());
//        products.setCount(Integer.pAdapter.getCountEt().toString);
        database.getProductsDao().Update(products);
        List<Products> list = database.getProductsDao().getProductsId(pAdapter.current_position);
        pAdapter.load_products(list);

        return null;


    }

}
