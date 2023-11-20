package federico.wedemeyer8.parcial.repositories;

import federico.wedemeyer8.parcial.models.Invoice;
import federico.wedemeyer8.parcial.models.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
