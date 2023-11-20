package federico.wedemeyer8.parcial.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "invoice_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceLineId")
    private Integer invoiceLineId;

    @Column(name = "UnitPrice")
    private Float unitPrice;

    @Column(name= "Quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InvoiceId")
    @JsonBackReference
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "TrackId")
    private Track track;

}
