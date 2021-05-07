package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.SalesTaxRate;
import java.util.List;

public interface SalesTaxDao {
    SalesTaxRate getSalesTaxByState(String state);
}