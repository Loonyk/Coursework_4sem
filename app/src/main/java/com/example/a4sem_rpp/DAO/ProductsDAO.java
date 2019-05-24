package com.example.a4sem_rpp.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.a4sem_rpp.modelDB.Products;

import java.util.List;

@Dao
public interface ProductsDAO {
    // Добавление заметок в бд
    @Insert
    void insertAll(Products... products);

    // Получение всех заметок из бд
    @Query("SELECT * FROM products")
    List<Products> getAllProducts();

    @Delete
    void delete(Products products);
}
