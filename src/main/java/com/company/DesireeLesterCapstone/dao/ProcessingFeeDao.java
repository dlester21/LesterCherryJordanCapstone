package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.ProcessingFee;

import java.util.List;

public interface ProcessingFeeDao {

    ProcessingFee getProcFeeByType(String productType);
}
