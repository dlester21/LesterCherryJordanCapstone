package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoice();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int id);

}
