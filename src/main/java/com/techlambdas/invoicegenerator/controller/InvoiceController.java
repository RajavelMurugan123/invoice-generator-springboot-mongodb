package com.techlambdas.invoicegenerator.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.techlambdas.invoicegenerator.model.Invoice;
import com.techlambdas.invoicegenerator.service.InvoiceService;
import com.techlambdas.invoicegenerator.service.PdfService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;
    private final PdfService pdfService;

    public InvoiceController(InvoiceService service, PdfService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @PostMapping
    public Invoice create(@RequestBody Invoice invoice) {
        return service.createInvoice(invoice);
    }

    @GetMapping
    public List<Invoice> getAll() {
        return service.getAllInvoices();
    }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable String id) {
        return service.getInvoiceById(id);
    }

    @PutMapping("/{id}")
    public Invoice update(@PathVariable String id, @RequestBody Invoice invoice) {
        return service.updateInvoice(id, invoice);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteInvoice(id);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> pdf(@PathVariable String id) throws Exception {
        byte[] pdf = pdfService.generateInvoicePdf(service.getInvoiceById(id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf")
                .body(pdf);
    }
}
