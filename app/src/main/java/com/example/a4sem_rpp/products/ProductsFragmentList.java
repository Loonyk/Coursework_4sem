package com.example.a4sem_rpp.products;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.a4sem_rpp.R;
import com.example.a4sem_rpp.modelDB.Products;

import java.util.ArrayList;

/** Фрагмент который будет отображать список товаров */
public class ProductsFragmentList extends Fragment {

    public ListView listLV;

    ArrayList<Products> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // list = (ArrayList<Products>)getArguments().getSerializable("list");
//        list = (ArrayList<Products>)getIntent().getExtras().getSerializable("list");
//        AdapterProducts adapterProducts = new AdapterProducts(ProductsFragmentList.this, list);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);

        /** ОШИБКА ТУТ */
        list = (ArrayList<Products>)getActivity().getIntent().getExtras().getSerializable("list");
        listLV = (ListView)view.findViewById(R.id.productsLV);
        AdapterProducts adapterProducts = new AdapterProducts(getContext(), list);
        listLV.setAdapter(adapterProducts);

        //обработка клика по активной кнопке
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateProducts.class);
                ArrayList<Products> products = new ArrayList<>();
                Products prod = new Products();
                prod.setTitle_pr("");
                prod.setPrice("");
                prod.setCount("");
                products.add(prod);
                intent.putExtra("list", products);
                startActivity(intent);
                //finish();
            }
        });
        return view;
    }

}
