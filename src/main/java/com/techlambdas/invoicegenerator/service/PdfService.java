package com.techlambdas.invoicegenerator.service;

import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.techlambdas.invoicegenerator.model.Invoice;
import com.techlambdas.invoicegenerator.model.Item;

@Service
public class PdfService {

    public byte[] generateInvoicePdf(Invoice invoice) throws Exception {

        Document doc = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, out);

        doc.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("INVOICE", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        doc.add(title);
        doc.add(new Paragraph(" "));

        PdfPTable info = new PdfPTable(2);
        info.setWidthPercentage(100);

        info.addCell(cell("Company Details", true));
        info.addCell(cell("Customer Details", true));

        info.addCell(cell(invoice.getCompany().getName(), false));
        info.addCell(cell(invoice.getCustomer().getName(), false));
        doc.add(info);

        doc.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        Stream.of("Item", "Qty", "Rate", "Tax", "Total")
                .forEach(h -> table.addCell(cell(h, true)));

        for (Item i : invoice.getItems()) {
            table.addCell(i.getItemName());
            table.addCell(String.valueOf(i.getQuantity()));
            table.addCell(String.valueOf(i.getRate()));
            table.addCell(i.getTaxPercentage() + "%");
            table.addCell(String.valueOf(i.getQuantity() * i.getRate()));
        }

        doc.add(table);
        doc.add(new Paragraph(" "));

        PdfPTable total = new PdfPTable(2);
        total.setWidthPercentage(40);
        total.setHorizontalAlignment(Element.ALIGN_RIGHT);

        total.addCell(cell("Subtotal", true));
        total.addCell(cell(String.valueOf(invoice.getSubTotal()), false));
        total.addCell(cell("Tax", true));
        total.addCell(cell(String.valueOf(invoice.getTaxAmount()), false));
        total.addCell(cell("Grand Total", true));
        total.addCell(cell(String.valueOf(invoice.getGrandTotal()), false));

        doc.add(total);
        doc.close();

        return out.toByteArray();
    }

    private PdfPCell cell(String text, boolean bold) {
        Font f = bold ? new Font(Font.HELVETICA, 10, Font.BOLD)
                : new Font(Font.HELVETICA, 10);
        PdfPCell c = new PdfPCell(new Phrase(text, f));
        c.setPadding(8);
        return c;
    }
}
