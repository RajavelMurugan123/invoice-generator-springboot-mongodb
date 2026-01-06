package com.techlambdas.invoicegenerator.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techlambdas.invoicegenerator.exception.InvoiceNotFoundException;
import com.techlambdas.invoicegenerator.model.Invoice;
import com.techlambdas.invoicegenerator.model.Item;
import com.techlambdas.invoicegenerator.repository.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository repo;

    public InvoiceService(InvoiceRepository repo) {
        this.repo = repo;
    }

    public Invoice createInvoice(Invoice invoice) {
        calculate(invoice);
        invoice.setInvoiceDate(LocalDate.now());
        return repo.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return repo.findAll();
    }

    public Invoice getInvoiceById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found: " + id));
    }

    public Invoice updateInvoice(String id, Invoice invoice) {
        Invoice existing = getInvoiceById(id);
        existing.setCompany(invoice.getCompany());
        existing.setCustomer(invoice.getCustomer());
        existing.setItems(invoice.getItems());
        calculate(existing);
        return repo.save(existing);
    }

    public void deleteInvoice(String id) {
        repo.deleteById(id);
    }

    private void calculate(Invoice invoice) {
        double sub = 0, tax = 0;
        for (Item i : invoice.getItems()) {
            double total = i.getQuantity() * i.getRate();
            sub += total;
            tax += total * (i.getTaxPercentage() / 100);
        }
        invoice.setSubTotal(sub);
        invoice.setTaxAmount(tax);
        invoice.setGrandTotal(sub + tax);
    }
}
