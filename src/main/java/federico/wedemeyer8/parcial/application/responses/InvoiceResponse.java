package federico.wedemeyer8.parcial.application.responses;
import federico.wedemeyer8.parcial.models.Invoice;
import federico.wedemeyer8.parcial.models.InvoiceItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceResponse {
    Integer invoiceId;
    CustomerResponse customer;
    LocalDateTime invoiceDate;
    String billingAddress;
    String billingCity;
    String billingState;
    String billingCountry;
    String billingPostalCode;
    Float total;
    List<InvoiceItem> invoiceItems;



    public static InvoiceResponse from(Invoice invoice){
        return InvoiceResponse.builder()
                .invoiceId(invoice.getInvoiceId())
                .customer(CustomerResponse.from(invoice.getCustomerId()))
                .invoiceDate(invoice.getInvoiceDate())
                .billingAddress(invoice.getBillingAddress())
                .billingCity(invoice.getBillingCity())
                .billingState(invoice.getBillingState())
                .billingCountry(invoice.getBillingCountry())
                .billingPostalCode(invoice.getBillingPostalCode())
                .invoiceItems(invoice.getInvoiceItems())
                .total(invoice.getTotal())

                .build();
    }
    //
    //.invoiceItems(invoice.getInvoiceItems())
}
