package com.techlambdas.invoicegenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.techlambdas.invoicegenerator.model.Invoice;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
