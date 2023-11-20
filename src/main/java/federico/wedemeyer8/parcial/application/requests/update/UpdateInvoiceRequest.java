package federico.wedemeyer8.parcial.application.requests.update;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UpdateInvoiceRequest {
    Integer customerId;
    String billingAddress;
    String billingCity;
    String billingState;
    String billingCountry;
    String billingPostalCode;
    List<Integer> invoiceItems;
}
