package com.example.a4sem_rpp.products;

import android.os.Bundle;
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
        list = (ArrayList<Products>)getArguments().getSerializable("list");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);

        listLV = (ListView)view.findViewById(R.id.productsLV);



        return view;
    }

}
