package com.company.DesireeLesterCapstone.service;

import com.company.DesireeLesterCapstone.dao.*;
import com.company.DesireeLesterCapstone.models.*;
import com.company.DesireeLesterCapstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

public class ServiceLayer {
    private ConsolesDao consolesDao;
    private GamesDao gamesDao;
    private InvoiceDao invoiceDao;
    private ProcessingFeeDao processingFeeDao;
    private SalesTaxDao salesTaxDao;
    private TShirtDao tShirtDao;
    @Autowired
    public ServiceLayer(ConsolesDao consolesDao, GamesDao gamesDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao, SalesTaxDao salesTaxDao, TShirtDao tShirtDao) {
        this.consolesDao = consolesDao;
        this.gamesDao = gamesDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxDao = salesTaxDao;
        this.tShirtDao = tShirtDao;
    }
    @Transactional
    public Invoice saveInvoice(Invoice viewModel) {
        //persist
        //consumer input
        Invoice invoice = new Invoice();
        invoice.setName(viewModel.getName());
        invoice.setStreet(viewModel.getStreet());
        invoice.setCity(viewModel.getCity());
        invoice.setState(viewModel.getState());
        invoice.setZipcode(viewModel.getZipcode());
        invoice.setItemType(viewModel.getItemType());
        invoice.setIdemId(viewModel.getIdemId());
        invoice.setQuantity(viewModel.getQuantity());
        invoice = invoiceDao.addInvoice(invoice);
        viewModel.setId(invoice.getId());
        //get unit price
        switch (invoice.getItemType())
        {
            case "Games":
                Games game = gamesDao.getGame(invoice.getIdemId());
                invoice.setUnitPrice(game.getPrice());
            case "Consoles":
                Consoles console = consolesDao.getConsole(invoice.getIdemId());
                invoice.setUnitPrice(console.getPrice());
            case "T-Shirts":
                TShirt tShirt = tShirtDao.getTShirt(invoice.getIdemId());
                invoice.setUnitPrice(tShirt.getPrice());
        }
        //get subtotal
        viewModel.setSubtotal(invoice.getUnitPrice().multiply(new BigDecimal(invoice.getQuantity())));
        invoice.setSubtotal(viewModel.getSubtotal());
        //get sales tax
        SalesTaxRate salesTaxRate = salesTaxDao.getSalesTaxByState(viewModel.getState());
        viewModel.setTax(salesTaxRate.getRate());
        invoice.setTax(viewModel.getTax());
        //add sales tax
        viewModel.setTotal(invoice.getSubtotal().add(invoice.getSubtotal().multiply(invoice.getTax())));
        invoice.setTotal(viewModel.getTotal());
        //get process fee
        ProcessingFee processingFee = processingFeeDao.getProcFeeByType(viewModel.getItemType());
        viewModel.setProcessingFee(processingFee.getFee());
        invoice.setProcessingFee(viewModel.getProcessingFee());
        //add process fee
        if (invoice.getQuantity() <= 10) {
            viewModel.setTotal(invoice.getSubtotal().add(invoice.getProcessingFee()));
        }
        else {
            viewModel.setTotal(invoice.getSubtotal().add(invoice.getProcessingFee().add(new BigDecimal(15.49))));
        }
        //set new total
        invoice.setTotal(viewModel.getTotal());

        return invoice;
    }
}
