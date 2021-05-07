package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.TShirt;

import java.util.List;

public interface TShirtDao {

    TShirt addTShirt(TShirt tShirt);

    TShirt getTShirt(int id);

    List<TShirt> getAllTShirts();

    void updateTShirt(TShirt tShirt);

    void deleteTShirt(int id);

    List<TShirt> getTShirtsByColor(String color);

    List<TShirt> getTShirtsBySize(String size);
}
