package federico.wedemeyer8.parcial.application.requests.update;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UpdateInvoiceItemRequest {
    Integer invoiceId;
    Integer quantity;
    Float unitPrice;
    Integer trackId;
}
