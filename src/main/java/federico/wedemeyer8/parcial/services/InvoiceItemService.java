package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Invoice;
import federico.wedemeyer8.parcial.models.InvoiceItem;
import federico.wedemeyer8.parcial.models.Track;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import federico.wedemeyer8.parcial.repositories.InvoiceItemRepository;
import federico.wedemeyer8.parcial.repositories.InvoiceRepository;
import federico.wedemeyer8.parcial.repositories.TrackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;
    private final IdentifierRepository identifierRepository;
    private final TrackRepository trackRepository;
    private final InvoiceRepository invoiceRepository;


    @Transactional
    public Optional<InvoiceItem> findById(Integer id) {
        return invoiceItemRepository.findById(id);
    }

    @Transactional
    public List<InvoiceItem> findAll() {
        return invoiceItemRepository.findAll();
    }

    @Transactional
    public InvoiceItem createInvoiceItem(Integer invoiceId, Integer trackId, Integer quantity) {
        Integer id = identifierRepository.nextValue("invoice_items");
        Track track = trackRepository.findById(trackId).orElseThrow(() -> new IllegalArgumentException("Track no encontrado"));
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new IllegalArgumentException("Invoice no encontrado"));
        Float unitPrice = track.getUnitPrice();

        InvoiceItem invoiceItem = new InvoiceItem(id, unitPrice, quantity, invoice, track);
        return invoiceItemRepository.save(invoiceItem);
    }


    @Transactional
    public InvoiceItem updateInvoiceItem(Integer id, Integer invoiceId, Integer trackId, Integer quantity) {

        InvoiceItem existingInvoiceItem = invoiceItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("InvoiceItem no encontrado"));
        Track track = trackRepository.findById(trackId).orElseThrow(() -> new IllegalArgumentException("Track no encontrado"));
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new IllegalArgumentException("Invoice no encontrado"));
        Float unitPrice = track.getUnitPrice();

        existingInvoiceItem.setInvoice(invoice);
        existingInvoiceItem.setQuantity(quantity);
        existingInvoiceItem.setTrack(track);
        existingInvoiceItem.setUnitPrice(unitPrice);

        invoiceItemRepository.save(existingInvoiceItem);
        return existingInvoiceItem;
    }

    @Transactional
    public void deleteInvoiceItem(Integer id) {
        invoiceItemRepository.deleteById(id);
    }


}
