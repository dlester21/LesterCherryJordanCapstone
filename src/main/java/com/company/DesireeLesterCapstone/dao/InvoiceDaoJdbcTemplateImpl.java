package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    private JdbcTemplate jdbcTemplate;

    //prepared statements
    private static final String INSERT_INVOICE_SQL =
            "INSERT INTO invoice (invoice_id, name, street, city, state, zipcode, item_type, unit_price, quantity, subtotal, tax, procressing_fee, total, " +
                    "quantity, subtotal, tax, processing_fee, total" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_SQL =
            "SELECT * FROM invoice WHERE invoice_id = ?";
    private static final String SELECT_ALL_INVOICE =
            "SELECT * FROM invoice";
    private static final String UPDATE_INVOICE_SQL =
            "UPDATE invoice SET name = ?, street = ?, city = ?, state = ?, zipcode = ?, item_type = ?, unit_price = ?, quantity = ?, subtotal = ?, tax = ?, processing_fee = ?, total = ?" + "WHERE invoice_de = ?";
    private static final String DELETE_INVOICE_SQL =
            "DELETE FROM invoice WHERE invoice_id";

    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL, invoice.getId(), invoice.getName(), invoice.getStreet(), invoice.getCity(), invoice.getState(), invoice.getZipcode(), invoice.getItemType(), invoice.getIdemId(), invoice.getUnitPrice(), invoice.getQuantity(), invoice.getSubtotal(), invoice.getTax(), invoice.getProcessingFee(), invoice.getTotal());

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        invoice.setId(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, id);
        } catch (EmptyResultDataAccessException e) {
            //If there are no invoices with the given id, return null
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE, this::mapRowToInvoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL, invoice.getName(), invoice.getStreet(), invoice.getCity(),
                invoice.getState(), invoice.getZipcode(), invoice.getItemType(), invoice.getIdemId(),
                invoice.getUnitPrice(), invoice.getQuantity(), invoice.getSubtotal(), invoice.getTax(),
                invoice.getProcessingFee(), invoice.getTotal(), invoice.getId());
    }

    @Override
    public void deleteInvoice(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    //Row mapper
    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("invoice_id"));
        invoice.setName(rs.getString("name"));
        invoice.setStreet(rs.getString("street"));
        invoice.setCity(rs.getString("city"));
        invoice.setState(rs.getString("state"));
        invoice.setZipcode(rs.getString("zipcode"));
        invoice.setItemType(rs.getString("item_type"));
        invoice.setIdemId(rs.getInt("item_id"));
        invoice.setUnitPrice(rs.getBigDecimal("unit_price"));
        invoice.setQuantity(rs.getInt("quantity"));
        invoice.setSubtotal(rs.getBigDecimal("subtotal"));
        invoice.setTax(rs.getBigDecimal("tax"));
        invoice.setProcessingFee(rs.getBigDecimal("processing_fee"));
        invoice.setTotal(rs.getBigDecimal("total"));
        return invoice;
    }
}
