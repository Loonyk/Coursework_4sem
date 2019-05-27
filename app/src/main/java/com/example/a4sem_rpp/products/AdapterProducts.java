package com.example.a4sem_rpp.products;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a4sem_rpp.R;
import com.example.a4sem_rpp.modelDB.Products;

import java.util.ArrayList;
import java.util.List;

public class AdapterProducts extends BaseAdapter {
    public Context context;
    public List<Products> list;
    public LayoutInflater inflater;
    public EditText titProdEt;    // имя в layout: tit_prod
    public EditText priceEt;     // имя в layout: price
    public EditText countEt;     // имя в layout: count
    public static int current_position = 0;

    private Products products;

    public TextView title;  //вью списка товаров в котором будет отражаться название

    public AdapterProducts(Context context, List<Products> prodList){
        this.context = context;
        list = prodList;
    }

    public Context getContext() {
        return context;
    }

    public EditText getTitProdEt() {
        return titProdEt;
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


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /** id ячейки в списке товаров titleProd*/

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_products, parent, false);

        /** для отражения в списке товаров названия*/
        title = (TextView)view.findViewById(R.id.titleProd);
        title.setText(this.list.get(position).getTitle_pr());

        titProdEt = (EditText) view.findViewById(R.id.tit_prod);
        priceEt = (EditText) view.findViewById(R.id.price);
        countEt = (EditText) view.findViewById(R.id.count);

        /** обработка нажатия на элемент списка с товарами*/
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_position = list.get(position).getId();
                ArrayList<Products> pos = new ArrayList<Products>();
                pos.add(list.get(position));
                Intent intent = new Intent(context, CreateProducts.class);
                intent.putExtra("list", pos);
                context.startActivity(intent);
            }
        });
        return view;
    }

    public void load_products(List<Products> products)
    {
        Intent intent=new Intent(this.context,CreateProducts.class);
        intent.putExtra("list",(ArrayList)products);
        this.context.startActivity(intent);
    }
}
