package nekrutenko;

import nekrutenko.repository.InvoiceRepository;
import nekrutenko.sevice.InformationAnalytics;
import nekrutenko.sevice.ShopService;

public class Main {
    public static void main(String[] args) {
        InvoiceRepository invoiceRepository = new InvoiceRepository();
        InformationAnalytics analytics = new InformationAnalytics(invoiceRepository);
        ShopService shopService = new ShopService(invoiceRepository);

        shopService.createFifteenInvoices();

        System.out.println("Saled every single type - " + analytics.getTypeOfInvoice(invoiceRepository.getAll()));
        System.out.println("--------------------------------------------------");
        System.out.println("The lowest invoice - " + analytics.minInvoice(invoiceRepository.getAll()));
        System.out.println("--------------------------------------------------");
        System.out.println("All invoices sum -  " + analytics.sumOfInvoices(invoiceRepository.getAll()));
        System.out.println("--------------------------------------------------");
        System.out.println("Retail invoices - " + analytics.retailInvoices(invoiceRepository.getAll()));
        System.out.println("--------------------------------------------------");
        System.out.println("Only one invoice - " + analytics.getOnlyOneTypeOfInvoices(invoiceRepository.getAll()));
        System.out.println("--------------------------------------------------");
        System.out.println("First three invoices - " + analytics.getOnlyThreeInvoices(invoiceRepository.getAll()));
        System.out.println("--------------------------------------------------");
        System.out.println("Customer age less than 18 - " + analytics.lowAgeInvoice(invoiceRepository.getAll()));
    }
}

