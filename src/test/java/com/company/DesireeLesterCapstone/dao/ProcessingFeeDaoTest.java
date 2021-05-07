package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.dao.ProcessingFeeDao;

import com.company.DesireeLesterCapstone.models.ProcessingFee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Test
    public void getProcFeeByTypeTest() {
        ProcessingFee processingFee = processingFeeDao.getProcFeeByType("Consoles");

        assertEquals(processingFee.getFee(), new BigDecimal("14.99"));

        processingFee = processingFeeDao.getProcFeeByType("Games");

        assertEquals(processingFee.getFee(), new BigDecimal("1.49"));

        processingFee = processingFeeDao.getProcFeeByType("T-Shirts");

        assertEquals(processingFee.getFee(), new BigDecimal("1.98"));

    }
}
