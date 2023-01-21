package nekrutenko.model.invoices;

import lombok.Getter;
import lombok.Setter;
import nekrutenko.model.Customer;
import nekrutenko.model.devices.Device;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Invoice {
    private List<Device> deviceList = new ArrayList<>();
    private Customer customer;
    private InvoiceTypes invoiceTypes;
    private static final int LIMIT_PRICE = 5000;

    public Invoice(Customer customer) {
        this.customer = customer;
    }

    public void addDevice(Device device) {
        deviceList.add(device);
        getSalesInfo();
    }

    public List<Device> getInvoiceDevice() {
        return deviceList;
    }

    private void getSalesInfo() {
        int devicePrice = getInvoiceDevice().stream().mapToInt(Device::getPrice).sum();
        if (devicePrice >= LIMIT_PRICE) {
            invoiceTypes = InvoiceTypes.WHOLESALE;
        } else {
            invoiceTypes = InvoiceTypes.RETAIL;
        }
    }

    @Override
    public String toString() {
        return "Invoice{" + " TIME[" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                "] USER_DATA[customer=" + customer +
                "] INVOICE_DATA" + getInvoiceDevice() +
                '}';
    }
}
