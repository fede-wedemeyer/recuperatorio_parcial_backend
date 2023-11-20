package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreateInvoiceItemRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateInvoiceItemRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateInvoiceRequest;
import federico.wedemeyer8.parcial.application.responses.InvoiceItemResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.services.InvoiceItemService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InvoiceItemController {
    private final InvoiceItemService invoiceItemService;

    @GetMapping("/api/invoice-items")
    public ResponseEntity<Object> getAll() {
        try {
            val invoiceItems = invoiceItemService.findAll()
                    .stream()
                    .map(InvoiceItemResponse::from)
                    .toList();
            return ResponseHandler.success(invoiceItems);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/api/invoice-items/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) {
        try {
            val invoiceItem = invoiceItemService.findById(id).map(InvoiceItemResponse::from);
            return ResponseHandler.success(invoiceItem);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping("/api/invoice-items")
    public ResponseEntity<Object> create(@RequestBody CreateInvoiceItemRequest request) {
        try {
            val invoiceItem = invoiceItemService.createInvoiceItem(request.getInvoiceId(), request.getTrackId(), request.getQuantity());
            return ResponseHandler.success(InvoiceItemResponse.from(invoiceItem));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PutMapping("/api/invoice-items/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateInvoiceItemRequest request, @PathVariable Integer id) {
        try {
            val invoiceItem = invoiceItemService.updateInvoiceItem(id, request.getInvoiceId(), request.getTrackId(), request.getQuantity());
            return ResponseHandler.success(InvoiceItemResponse.from(invoiceItem));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/api/invoice-items/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            invoiceItemService.deleteInvoiceItem(id);
            return ResponseHandler.success("Item eliminated");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
