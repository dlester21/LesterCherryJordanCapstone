package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao {
    private JdbcTemplate jdbcTemplate;
    //prepared statement to interact with the SQL Server
    private static final String SELECT_PROCESS_FEE_BY_TITLE_SQL =
            "SELECT * FROM processing_fee WHERE product_type = ?";

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProcessingFee getProcFeeByType(String productType) {

        try {
            return jdbcTemplate.queryForObject(SELECT_PROCESS_FEE_BY_TITLE_SQL, this::mapRowToProcessingFee, productType);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }


    }
    //row mapper
    private ProcessingFee mapRowToProcessingFee (ResultSet rs, int rowNum) throws SQLException
    {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType(rs.getString("product_type"));
        processingFee.setFee(rs.getBigDecimal("fee"));
        return processingFee;
    }
}
