package federico.wedemeyer8.parcial.application.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = {@JsonIgnore})
public class CreateInvoiceRequest {

    Integer customerId;
    String billingAddress;
    String billingCity;
    String billingState;
    String billingCountry;
    String billingPostalCode;
    List<Integer> invoiceItems;
}
