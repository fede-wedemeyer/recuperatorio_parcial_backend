package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Customer;
import federico.wedemeyer8.parcial.models.Invoice;
import federico.wedemeyer8.parcial.models.InvoiceItem;
import federico.wedemeyer8.parcial.repositories.CustomerRepository;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import federico.wedemeyer8.parcial.repositories.InvoiceItemRepository;
import federico.wedemeyer8.parcial.repositories.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final IdentifierRepository identifierRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Transactional
    public Optional<Invoice> findById(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Transactional
    public Invoice createInvoice(Integer customerId, String billingAddress,
                          String billingCity, String billingState, String billingCountry, String billingPostalCode, List<Integer> invoiceItems) {
        Integer id = identifierRepository.nextValue("invoices");
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        List<InvoiceItem> invoceItemsList = new ArrayList<>();
        for (Integer invoiceItemId : invoiceItems) {
            invoceItemsList.add(invoiceItemRepository.findById(invoiceItemId).orElseThrow(() -> new IllegalArgumentException("Item no encontrado")));
        }

        Float total = calcularTotal(invoceItemsList);
        LocalDateTime invoiceDate = LocalDateTime.now();

        Invoice invoice = new Invoice(id, invoiceDate, billingAddress, billingCity, billingState, billingCountry, billingPostalCode, total, customer, invoceItemsList);
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice updateInvoice(Integer id, Integer customerId, String billingAddress,
                                 String billingCity, String billingState, String billingCountry, String billingPostalCode, List<Integer> invoiceItems) {

        Invoice existingInvoice = invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice no encontrado"));

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        List<InvoiceItem> invoceItemsList = new ArrayList<>();
        for (Integer invoiceItemId : invoiceItems) {
            invoceItemsList.add(invoiceItemRepository.findById(invoiceItemId).orElseThrow(() -> new IllegalArgumentException("Item no encontrado")));
        }

        existingInvoice.setCustomerId(customer);
        //existingInvoice.setInvoiceDate(invoiceDate);  LA FECHA DE CREACIÓN DEL INVOICE NO TENDRÍA QUE PODER SER MODIFICADA UNA VEZ CREADA
        existingInvoice.setBillingAddress(billingAddress);
        existingInvoice.setBillingCity(billingCity);
        existingInvoice.setBillingState(billingState);
        existingInvoice.setBillingCountry(billingCountry);
        existingInvoice.setBillingPostalCode(billingPostalCode);
        existingInvoice.setTotal(calcularTotal(invoceItemsList));
        existingInvoice.setInvoiceItems(invoceItemsList);

        invoiceRepository.save(existingInvoice);
        return existingInvoice;
    }

    @Transactional
    public void deleteInvoice(Integer id) {
        Invoice existingInvoice = invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice no encontrado"));
        invoiceRepository.delete(existingInvoice);
    }




    public Float calcularTotal(List<InvoiceItem> invoiceItems) {
        Float total = 0f;

        for (InvoiceItem invoiceItem : invoiceItems) {
            total += invoiceItem.getUnitPrice() * invoiceItem.getQuantity();
        }
        return total;
    }

}
