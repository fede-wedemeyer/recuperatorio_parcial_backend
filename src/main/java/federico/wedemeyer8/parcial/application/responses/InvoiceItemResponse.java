package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.InvoiceItem;
import federico.wedemeyer8.parcial.models.Track;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceItemResponse {
    Integer invoiceLineId;
    Integer invoiceId;
    Integer quantity;
    Float unitPrice;
    TrackResponse track;



    public static InvoiceItemResponse from(InvoiceItem invoiceItem) {
        return InvoiceItemResponse.builder()
                .invoiceId((invoiceItem.getInvoice()).getInvoiceId())
                .invoiceLineId(invoiceItem.getInvoiceLineId())
                .quantity(invoiceItem.getQuantity())
                .track(TrackResponse.from(invoiceItem.getTrack()))
                .unitPrice(invoiceItem.getUnitPrice())
                .build();


    }
}
