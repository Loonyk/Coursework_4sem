package com.example.a4sem_rpp.products;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.a4sem_rpp.AsyncTask.AsyncProdInsert;
import com.example.a4sem_rpp.MenuActivity;
import com.example.a4sem_rpp.R;
import com.example.a4sem_rpp.modelDB.Products;

import java.util.ArrayList;
import java.util.List;

public class CreateProducts extends AppCompatActivity {

    private EditText titleET;
    private EditText priceEt;
    private EditText countEt;
    private Products products;
    private TextInputLayout titleTil;
    private TextInputLayout priceTil;
    private TextInputLayout countTil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_products);

        products = ((ArrayList<Products>)getIntent().getExtras().getSerializable("list")).get(0);

        titleET = findViewById(R.id.tit_prod);
        priceEt = findViewById(R.id.price);
        countEt = findViewById(R.id.count);

        titleTil = findViewById(R.id.name_til);
        priceTil = findViewById(R.id.price_til);
        countTil = findViewById(R.id.count_til);

        titleET.setText(products.getTitle_pr());
        priceEt.setText(products.getPrice());
        countEt.setText(products.getCount());
    }

    @Override    //кнопка сохранить в тулбаре
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.create_note, menu);
        return true;
    }

    /** метод сохранения товара */
    private void saveProd(){
        String title = titleET.getText().toString().trim();
        String price = priceEt.getText().toString().trim();
        String count = countEt.getText().toString().trim();

        boolean isCorrect = true;

        if (TextUtils.isEmpty(title)) {
            isCorrect = false;

            titleTil.setError("Необходимо заполнить поле");
            titleTil.setErrorEnabled(true);
        } else {
            titleTil.setErrorEnabled(false);
        }

//        if (TextUtils.isEmpty(count)) {
//            isCorrect = false;
//
//            countEt.setError("Необходимо заполнить поле");
//
//            //countEt.setErrorEnabled(true);
//        } else {
//            countEt.setErrorEnabled(false);
//        }

        if (isCorrect){
            //код вставки заметки в БД + завершение работы активити и вывод списка
            AsyncProdInsert as= new AsyncProdInsert();
            as.execute(this);
        }
    }

    @Override  //обработка клика на кнопку сохранить
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                saveProd();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** !!!!!!!!*/
    public void openProd(List<Products> list) {
        Intent intent = new Intent(CreateProducts.this, ProductsFragmentList.class);
        intent.putExtra("list",(ArrayList)list);
        startActivity(intent);
    }

    public EditText getTitleET() {
        return titleET;
    }

    public EditText getPriceEt() {
        return priceEt;
    }

    public EditText getCountEt() {
        return countEt;
    }

    public Products getProducts() {
        return products;
    }


}
