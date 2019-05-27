package com.example.a4sem_rpp.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.a4sem_rpp.modelDB.Products;

import java.util.List;

@Dao
public interface ProductsDAO {
    // Добавление заметок в бд
    @Insert
    void insertAll(Products... products);

    @Update
    void Update(Products products);

    // Получение всех заметок из бд
    @Query("SELECT * FROM products")
    List<Products> getAllProducts();

    @Query("SELECT * FROM notes WHERE id=:id_prod")
    List<Products>getProductsId(int id_prod);

    @Delete
    void delete(Products products);
}
