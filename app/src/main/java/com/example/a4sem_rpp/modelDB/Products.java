package com.example.a4sem_rpp.modelDB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Products {
    @PrimaryKey(autoGenerate = true)int id;
    String title_pr;
    int price;
    int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_pr() {
        return title_pr;
    }

    public void setTitle_pr(String title_pr) {
        this.title_pr = title_pr;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
