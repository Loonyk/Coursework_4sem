package com.example.a4sem_rpp.AsyncTask;

import android.os.AsyncTask;

import com.example.a4sem_rpp.db.AppDatabase;
import com.example.a4sem_rpp.modelDB.Products;
import com.example.a4sem_rpp.products.CreateProducts;

import java.util.List;

/** AsyncTask для занесения в БД товаров */
public class AsyncProdInsert extends AsyncTask<CreateProducts, Void, Void> {


    @Override
    protected Void doInBackground(CreateProducts... createProducts) {

        CreateProducts createPr = createProducts[0];
        AppDatabase database = AppDatabase.getDatabase(createPr);

        Products prod =new Products();
        prod.setTitle_pr(createPr.getTitleET().getText().toString());
        prod.setPrice(createPr.getPriceEt().getText().toString());
        prod.setCount(createPr.getCountEt().getText().toString());
        database.getProductsDao().insertAll(prod);
        List<Products> list =database.getProductsDao().getAllProducts();
        createPr.openProd(list);

        return null;
    }
}
