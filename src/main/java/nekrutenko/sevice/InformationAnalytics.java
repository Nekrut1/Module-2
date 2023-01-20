package nekrutenko.sevice;

import nekrutenko.model.devices.Device;
import nekrutenko.model.devices.DeviceType;
import nekrutenko.model.invoices.Invoice;
import nekrutenko.model.invoices.InvoiceTypes;
import nekrutenko.repository.InvoiceRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InformationAnalytics {
    private final InvoiceRepository invoiceRepository;

    public InformationAnalytics(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Map<DeviceType, Long> getTypeOfInvoice(List<Invoice> invoiceList) {
        return invoiceList.stream()
                .flatMap(x -> x.getInvoiceDevice().stream())
                .collect(Collectors.groupingBy(Device::getDeviceType, Collectors.counting()));
    }

    public int minInvoice(List<Invoice> invoiceList) {
        return invoiceList.stream()
                .min(Comparator.comparingInt(x -> x.getInvoiceDevice().stream()
                        .mapToInt(Device::getPrice).sum()))
                .stream().peek(invoice -> System.out.println(invoice.getCustomer()))
                .flatMap(x -> x.getInvoiceDevice().stream())
                .mapToInt(Device::getPrice).sum();
    }

    public int sumOfInvoices(List<Invoice> invoiceList) {
        return invoiceList.stream()
                .flatMap(x -> x.getInvoiceDevice().stream())
                .mapToInt(Device::getPrice).sum();
    }

    public int retailInvoices(List<Invoice> invoiceList) {
        return (int) invoiceList.stream()
                .filter(x -> x.getInvoiceTypes().equals(InvoiceTypes.RETAIL))
                .count();
    }

    public List<Invoice> getOnlyOneTypeOfInvoices(List<Invoice> invoiceList) {
        return invoiceList.stream()
                .filter(y -> 1 == y.getInvoiceDevice().stream()
                        .map(Device::getDeviceType)
                        .distinct()
                        .toArray()
                        .length).collect(Collectors.toList());
    }

    public List<Invoice> getOnlyThreeInvoices(List<Invoice> invoiceList) {
        return invoiceList.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Invoice> lowAgeInvoice(List<Invoice> invoiceList) {
        return invoiceList.stream()
                .filter(x -> x.getCustomer().getAge() < 18)
                .peek(x -> x.setInvoiceTypes(InvoiceTypes.LOW_AGE))
                .collect(Collectors.toList());
    }
}
