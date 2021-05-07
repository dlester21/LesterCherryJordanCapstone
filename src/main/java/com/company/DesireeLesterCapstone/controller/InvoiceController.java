package com.company.DesireeLesterCapstone.controller;


import com.company.DesireeLesterCapstone.dao.*;
import com.company.DesireeLesterCapstone.models.Invoice;
import com.company.DesireeLesterCapstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class InvoiceController {
    @Autowired
    InvoiceDao invoiceDao;
    ConsolesDao consolesDao;
    GamesDao gamesDao;
    TShirtDao tShirtDao;
    SalesTaxDao salesTaxDao;
    ProcessingFeeDao processingFeeDao;

    public InvoiceController(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice makeInvoice(@RequestBody Invoice invoice) {
        return invoiceDao.addInvoice(invoice);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        List<Invoice> returnList = new ArrayList<>();
        ServiceLayer service = new ServiceLayer(consolesDao, gamesDao, invoiceDao, processingFeeDao, salesTaxDao, tShirtDao);
        for (Invoice invoice : invoiceDao.getAllInvoice()) {
            returnList.add(service.saveInvoice(invoice));
        }
        return returnList;
    }

}

