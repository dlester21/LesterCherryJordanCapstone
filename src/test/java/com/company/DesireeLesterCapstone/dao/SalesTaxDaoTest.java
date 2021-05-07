package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.SalesTaxRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.math.BigDecimal;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxDaoTest {
    @Autowired
    SalesTaxDao salesTaxDao;
    @Test
    public void getSalesTaxByState() {
        SalesTaxRate salesTaxRate = salesTaxDao.getSalesTaxByState("AL");
        assertEquals(salesTaxRate.getRate(), new BigDecimal("0.05"));
        salesTaxRate = salesTaxDao.getSalesTaxByState("MT");
        assertEquals(salesTaxRate.getRate(), new BigDecimal("0.03"));
        salesTaxRate = salesTaxDao.getSalesTaxByState("VA");
        assertEquals(salesTaxRate.getRate(), new BigDecimal("0.06"));
    }
}
