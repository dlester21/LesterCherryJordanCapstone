package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class SalesTaxDaoJdbcTemplateImpl implements SalesTaxDao {
    private JdbcTemplate jdbcTemplate;
    //prepared statement to interact with the SQL Server
    private static final String SELECT_SALES_TAX_BY_STATE_SQL =
            "SELECT * FROM sales_tax_rate WHERE state = ?";
    @Autowired
    public SalesTaxDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public SalesTaxRate getSalesTaxByState(String state) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SALES_TAX_BY_STATE_SQL, this::mapRowToSalesTaxRate, state);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException
    {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));
        return salesTaxRate;
    }
}
