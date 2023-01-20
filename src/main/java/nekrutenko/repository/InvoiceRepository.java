package nekrutenko.repository;

import nekrutenko.model.invoices.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {
    private final List<Invoice> invoiceList = new ArrayList<>();

    public void save(Invoice invoice) {
        invoiceList.add(invoice);
    }

    public List<Invoice> getAll() {
        return invoiceList;
    }
}
